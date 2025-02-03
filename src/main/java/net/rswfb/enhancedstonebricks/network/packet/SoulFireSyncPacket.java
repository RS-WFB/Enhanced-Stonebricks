package net.rswfb.enhancedstonebricks.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SoulFireSyncPacket(int entityId, boolean hasSoulFire) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation("enhancedstonebricks", "soul_fire_sync");

    // 编码（服务端发送）
    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeBoolean(hasSoulFire);
    }

    // 解码（客户端接收）
    public static SoulFireSyncPacket read(FriendlyByteBuf buffer) {
        return new SoulFireSyncPacket(buffer.readInt(), buffer.readBoolean());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
