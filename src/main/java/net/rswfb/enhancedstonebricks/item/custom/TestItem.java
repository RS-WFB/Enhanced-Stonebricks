package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;

public class TestItem extends Item {
    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        CompoundTag nbt = new CompoundTag();
        if (!itemstack.getTag().contains("Test")) {
            nbt.putBoolean("Test", true);
            itemstack.setTag(nbt);
        }


        if (itemstack.getTag().getBoolean("Test")) {
            itemstack.getTag().putBoolean("Test", false);
        } else {
            itemstack.getTag().putBoolean("Test", true);
        }
        EnhancedStonebricks.LOGGER.info(String.valueOf(itemstack.getTag().getBoolean("Test")));
        return InteractionResultHolder.success(itemstack);
    }
}
