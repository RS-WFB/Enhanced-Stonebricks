package net.rswfb.enhancedstonebricks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onArrowFired(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow) {
            // 检查射手是否手持自定义弓
            if (arrow.getOwner() instanceof Player player) {
                ItemStack bow = player.getUseItem();
                if (bow.getItem() == ModItems.SOUL_BOW.get()) {
                    // 标记箭矢为灵魂火轨迹
                    arrow.getPersistentData().putBoolean("soul_fire", true);
                    SoulFireSyncPacket packet = new SoulFireSyncPacket(arrow.getId(), true);
                    // 发送给所有追踪该箭矢的玩家（包括射箭的玩家）
                    PacketDistributor.PLAYER.with((ServerPlayer) player).send(packet);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(TickEvent.LevelTickEvent event) {
        if (Minecraft.getInstance().level != null) {
            for (Entity entity : Minecraft.getInstance().level.entitiesForRendering()) {
                CompoundTag nbt = entity.getPersistentData();
                if (entity.getPersistentData().getBoolean("soul_fire")) {
                    Level level = entity.level();
                    if (entity instanceof AbstractArrow arrow) {
                        if (level.isClientSide) {
                            // 生成拖尾粒子
                            CompoundTag isg = arrow.getPersistentData().getCompound("inGround");
                            arrow.addAdditionalSaveData(isg);
                            if (!isg.getBoolean("inGround")) {
                                for (int i = 0; i < 2; i++) {
                                    double offset = i * 0.1;
                                    level.addParticle(
                                            ParticleTypes.SOUL_FIRE_FLAME,
                                            arrow.getX() - arrow.getDeltaMovement().x * offset,
                                            arrow.getY() - arrow.getDeltaMovement().y * offset,
                                            arrow.getZ() - arrow.getDeltaMovement().z * offset,
                                            0, 0, 0
                                    );
                                }
                            }
                        }
                    }
                }
            };
        }
    }

    // 玩家加入时同步所有现有灵魂火箭
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        ServerLevel level = (ServerLevel) player.level();

        level.getAllEntities().forEach(entity -> {
            if (entity instanceof AbstractArrow arrow &&
                    arrow.getPersistentData().getBoolean("soul_fire")) {
                // 直接向该玩家发送数据包
                SoulFireSyncPacket packet = new SoulFireSyncPacket(arrow.getId(), true);
                PacketDistributor.PLAYER.with(player).send(packet);
            }
        });
    }
}
