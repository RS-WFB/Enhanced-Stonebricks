package net.rswfb.enhancedstonebricks.event.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.rswfb.enhancedstonebricks.entity.projectile.CustonDragonFireball;

import javax.annotation.Nullable;

public class Funcs {
    public static void summonDragonFireball(Level level, Entity target, @Nullable LivingEntity owner) {
        // 生成位置（目标正上方20格）
        Vec3 targetPos = target.position();
        double spawnX = targetPos.x;
        double spawnY = targetPos.y + 20.0;
        double spawnZ = targetPos.z;

        // 创建龙息弹（方向参数为初始运动方向）
        DragonFireball fireball = new CustonDragonFireball(
                level,
                owner, // 发射者（可空）
                0, 0, 0 // 生成位置
                // 初始运动方向（向下）
        );
        fireball.setPos(spawnX, spawnY, spawnZ);

        // 设置龙息弹属性
        fireball.setNoGravity(false); // 启用重力（下坠效果）
        fireball.setDeltaMovement(0, -4.5, 0); // 调整下落速度

        // 计算水平偏移（追踪目标位置）
        Vec3 horizontalMotion = new Vec3(
                0, // 水平X方向速度
                0,
                0  // 水平Z方向速度
        );
        fireball.setDeltaMovement(fireball.getDeltaMovement().add(horizontalMotion));

        // 添加到世界
        level.addFreshEntity(fireball);

        // 播放音效（服务端）
        level.playSound(
                null, // 对所有玩家播放
                spawnX, spawnY, spawnZ,
                SoundEvents.ENDER_DRAGON_SHOOT, // 龙息音效
                SoundSource.HOSTILE,
                1.0F, 1.0F
        );
    }
    public static boolean isFullSetOfArmor(Player pPlayer) {
        ArmorItem helmet, chestplate, leggings, boots;

        try{
            helmet = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem();
            chestplate = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem();
            leggings = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.LEGS).getItem();
            boots = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.FEET).getItem();
            return helmet.getMaterial() == chestplate.getMaterial() &&
                    leggings.getMaterial() == boots.getMaterial() &&
                    chestplate.getMaterial() == leggings.getMaterial();
        } catch (ClassCastException e) {
            return false;
        }
    }
    public static ArmorMaterial getArmorMaterial(Player pPlayer) {
        return ((ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem()).getMaterial();
    }

    public static boolean roll(double possibility) {
        return Math.random() <= possibility;
    }

    public static Vec3 calculateTargetPosition(Player player, double distance) {
        float yaw = player.getYRot(); // 水平旋转角（偏航角）
        float pitch = player.getXRot(); // 垂直旋转角（俯仰角）

        // 计算方向向量
        double x = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        double y = -Math.sin(Math.toRadians(pitch));
        double z = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

        // 归一化方向向量
        Vec3 direction = new Vec3(x, y, z).normalize();

        // 计算目标位置（玩家当前位置 + 方向向量 * 距离）
        return player.position().add(direction.scale(distance));
    }

    public static boolean isPathObstructed(Level level, Vec3 startPos, Vec3 endPos) {
        // 定义射线检测参数
        ClipContext context = new ClipContext(
                startPos,
                endPos,
                ClipContext.Block.COLLIDER, // 检测碰撞箱
                ClipContext.Fluid.NONE,     // 忽略液体
                CollisionContext.empty()
        );

        // 执行射线检测
        BlockHitResult hitResult = level.clip(context);

        // 如果检测到方块，则路径被阻挡
        return hitResult.getType() != HitResult.Type.MISS;
    }
    public static void attemptTeleport(Player player, double distance) {
        Level level = player.level();
        Vec3 startPos = player.position();
        Vec3 targetPos = calculateTargetPosition(player, distance);

        // 检测路径是否被阻挡
        if (isPathObstructed(level, startPos, targetPos)) {
            player.displayClientMessage(Component.literal("路径被阻挡！"), true);
            return;
        }

        // 执行传送
        player.teleportTo(targetPos.x, targetPos.y, targetPos.z);
        player.displayClientMessage(Component.literal("传送成功！"), true);
    }
}
