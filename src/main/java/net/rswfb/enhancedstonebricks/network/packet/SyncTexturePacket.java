package net.rswfb.enhancedstonebricks.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public record SyncTexturePacket(int entityId, String pKey, int pswitch) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation("enhancedstonebricks", "sync_texture_packet");

    // 编码（服务端发送）
    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
        buffer.writeUtf(pKey);
        buffer.writeInt(pswitch);
    }

    // 解码（客户端接收）
    public static SyncTexturePacket read(FriendlyByteBuf buffer) {
        return new SyncTexturePacket(buffer.readInt(), buffer.readUtf(), buffer.readInt());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
