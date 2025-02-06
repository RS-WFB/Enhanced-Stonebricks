package net.rswfb.enhancedstonebricks.item.custom.Armors;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.rswfb.enhancedstonebricks.item.custom.ArmorPackageItem;

public class EmptyArmorPackageItem extends ArmorPackageItem {
    public EmptyArmorPackageItem(Properties properties) {
        super(properties);
        this.isEmpty = true;
    }
}
