package net.rswfb.enhancedstonebricks.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.network.handler.ClientPacketHandler;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    @SubscribeEvent
    // 注册数据包通道
    public static void registerPackets(final RegisterPayloadHandlerEvent event) {
        IPayloadRegistrar registrar = event.registrar(EnhancedStonebricks.MODID)
                .versioned("1.0"); // 可选版本控制
        // 注册服务端 -> 客户端的数据包
        registrar.play(
                SoulFireSyncPacket.ID,
                SoulFireSyncPacket::read,
                handler -> handler.client(ClientPacketHandler::handleSoulFireSync)
        );
    }
}
