package net.rswfb.enhancedstonebricks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.custom.Requiem;
import net.rswfb.enhancedstonebricks.item.custom.tool.ModArmorMaterial;
import net.rswfb.enhancedstonebricks.network.packet.SyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncTexturePacket;
import org.apache.logging.log4j.LogManager;

import static net.rswfb.enhancedstonebricks.event.utils.Funcs.isFullSetOfArmor;
import static net.rswfb.enhancedstonebricks.event.utils.Funcs.getArmorMaterial;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class ArmorSetEvent {


    @SubscribeEvent
    public static void onArmorSetEvent(TickEvent.PlayerTickEvent event) {
        Level level = event.player.level();
        int armor_set = 0;
        if (!level.isClientSide()) {
            if( level.getGameTime() % 2 == 0) {
                ServerPlayer player = (ServerPlayer) event.player;
                    if (isFullSetOfArmor(player)){
                        if (getArmorMaterial(player) == ModArmorMaterial.STORMSTRIDER){
                            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 2));
                        }
                }
            }
        }
    }
}
