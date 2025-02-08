package net.rswfb.enhancedstonebricks.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.client.KeyBindings;
import net.rswfb.enhancedstonebricks.item.custom.tool.ModArmorMaterial;
import net.rswfb.enhancedstonebricks.network.packet.SoulFireSyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncPacket;
import net.rswfb.enhancedstonebricks.network.packet.SyncTexturePacket;

import static net.rswfb.enhancedstonebricks.event.utils.Funcs.*;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class ArmorSetEvent {


    @SubscribeEvent
    public static void onArmorSetEvent(TickEvent.PlayerTickEvent event) {
        Level level = event.player.level();
        int armor_set = 0;
        if (!level.isClientSide()) {
            ServerPlayer player = (ServerPlayer) event.player;
            if (isFullSetOfArmor(player)){
                if (getArmorMaterial(player) == ModArmorMaterial.STORMSTRIDER){
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 2));
                    event.player.getPersistentData().putInt("armor_set", 1);
                } else {
                    event.player.getPersistentData().putInt("armor_set", 0);
                }
            } else {
                event.player.getPersistentData().putInt("armor_set", 0);
            }
            if (player.getPersistentData().getBoolean("armor_effect")){
                if (isFullSetOfArmor(player)) {
                    if (getArmorMaterial(player) == ModArmorMaterial.STORMSTRIDER) {
                        attemptTeleport(player, 3);
                    }
                }
                player.getPersistentData().putBoolean("armor_effect", false);
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerHit(LivingDamageEvent event) {
        Entity target = event.getEntity();
        if (target instanceof Player player){
            int armor_set = 0;
            try {
                armor_set = player.getPersistentData().getInt("armor_set");
            } catch (Exception ignored){}
            switch (armor_set) {
                case 0:break;
                case 1:
                    if (roll(0.3)) {
                        event.setCanceled(true);
                    }
                break;
            }
        }
    }
    @SubscribeEvent
    public static void onKeyInput(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();

        if (!level.isClientSide()) {
            if (KeyBindings.ARMOR_EFFECT.consumeClick()) {
                player.getPersistentData().putBoolean("armor_effect", true);
            }
        }
    }
}
