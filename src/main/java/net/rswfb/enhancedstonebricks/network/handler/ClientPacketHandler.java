package net.rswfb.enhancedstonebricks.network.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.custom.Requiem;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncTexturePacket;

public class ClientPacketHandler {
    public static void handleSoulFireSync(SoulFireSyncPacket packet, IPayloadContext context) {
        context.workHandler().execute(() -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
                if (entity == null) {

                    // 延迟 1 秒后重试（20 ticks = 1秒）
                    DelayedTaskManager.scheduleTask(() -> {
                        Entity retryEntity = Minecraft.getInstance().level.getEntity(packet.entityId());
                        if (retryEntity != null) {
                            retryEntity.getPersistentData().putBoolean("soul_fire", true);
                        }
                    }, 1); // 延迟 10 ticks
                } else if (entity instanceof AbstractArrow arrow) {
                    arrow.getPersistentData().putBoolean("soul_fire", true);
                }
            }
        });
    }
    public static void handleSync(SyncPacket packet, IPayloadContext context) {
        context.workHandler().execute(() -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
                if (entity == null) {
                    // 延迟 1 秒后重试（20 ticks = 1秒）
                    DelayedTaskManager.scheduleTask(() -> {
                        Entity retryEntity = Minecraft.getInstance().level.getEntity(packet.entityId());
                        if (retryEntity != null) {
                            retryEntity.getPersistentData().putBoolean(packet.pKey(), true);
                        }
                    }, 1); // 延迟 10 ticks

                } else {
                    entity.getPersistentData().putBoolean(packet.pKey(), true);
                }
            }
        });
    }
    public static void handleSyncTexturePacket(SyncTexturePacket packet, IPayloadContext context) {
        context.workHandler().execute(() -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
                if (entity instanceof Player player) {
                    if (packet.pKey().equals("armor_set")){
                        EnhancedStonebricks.LOGGER.info("armor_set");
                        switch (packet.pswitch()){
                            case 0: break;
                            case 1: player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 2));
                            break;
                        }
                    } else {
                        ItemStack item = player.getMainHandItem();
                        CompoundTag nbt = item.getOrCreateTag();
                        nbt.putInt(packet.pKey(), packet.pswitch());
                    }
                }
            }
        });
    }
}