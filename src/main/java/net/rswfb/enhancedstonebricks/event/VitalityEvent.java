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
            // 检查玩家是否持有自定义图腾（背包或快捷栏）
            if (hasCustomTotem(player)) {
                // 取消死亡
                event.setCanceled(true);

                // 触发不死效果
                applyTotemEffects(player);

                // 消耗物品（可选）
                consumeTotem(player);
            }
        }
    }

    // 检查玩家是否持有自定义图腾
    private static boolean hasCustomTotem(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == ModItems.CORE_OF_VITALITY.get()) {
                return true;
            }
        }
        return false;
    }

    // 应用不死图腾效果
    private static void applyTotemEffects(Player player) {
        player.setHealth(1.0F); // 恢复生命值
        player.removeAllEffects(); // 清除负面状态
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1)); // 恢复II
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1)); // 伤害吸收
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F); // 播放音效

    }

    // 消耗图腾（删除物品）
    private static void consumeTotem(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() == ModItems.CORE_OF_VITALITY.get()) {
                stack.shrink(1); // 减少物品数量
                break;
            }
        }
    }
}

