package net.rswfb.enhancedstonebricks.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SyncPacket(int entityId, String pKey, boolean pswitch) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation("enhancedstonebricks", "sync_packet");

    // 编码（服务端发送）
    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeUtf(pKey);
        buffer.writeBoolean(pswitch);
    }

    // 解码（客户端接收）
    public static SyncPacket read(FriendlyByteBuf buffer) {
        return new SyncPacket(buffer.readInt(), buffer.readUtf(), buffer.readBoolean());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
