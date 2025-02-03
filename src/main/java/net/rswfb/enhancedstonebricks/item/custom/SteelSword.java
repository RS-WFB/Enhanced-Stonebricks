package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.rswfb.enhancedstonebricks.item.custom.tool.ModItemTiers;

public class SteelSword extends SwordItem {
    public SteelSword() {
        super(ModItemTiers.STEEL, 3, -2.4F, new Item.Properties());
    }
}
