package net.rswfb.enhancedstonebricks.network.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;

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
}