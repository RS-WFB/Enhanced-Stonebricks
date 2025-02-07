package net.rswfb.enhancedstonebricks.event.utils;

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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
        helmet = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem();
        chestplate = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.CHEST).getItem();
        leggings = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.LEGS).getItem();
        boots = (ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.FEET).getItem();
        return helmet.getMaterial() == chestplate.getMaterial() &&
                leggings.getMaterial() == boots.getMaterial() &&
                chestplate.getMaterial() == leggings.getMaterial();
    }
    public static ArmorMaterial getArmorMaterial(Player pPlayer) {
        return ((ArmorItem) pPlayer.getItemBySlot(EquipmentSlot.HEAD).getItem()).getMaterial();
    }
}
