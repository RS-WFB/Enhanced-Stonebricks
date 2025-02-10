package net.rswfb.enhancedstonebricks.event;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID)
public class VitalityEvent {
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (hasCustomTotem(player)) {
                event.setCanceled(true);
                applyTotemEffects(player);
                consumeTotem(player);
            }
        }
    }
    private static boolean hasCustomTotem(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == ModItems.CORE_OF_VITALITY.get()) {
                return true;
            }
        }
        return false;
    }
    private static void applyTotemEffects(Player player) {
        player.setHealth(1.0F);
        player.removeAllEffects();
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

    }
    private static void consumeTotem(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() == ModItems.CORE_OF_VITALITY.get()) {
                stack.shrink(1);
                break;
            }
        }
    }
}

