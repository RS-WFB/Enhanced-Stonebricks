package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Crucibles extends Item {
    ItemContainer original;
    Stream<ItemStack> items;
    List<ItemStack> ingredients;

    public Crucibles(Properties props, ItemContainer container) {
        super(props);
        this.original = container;
        this.items = container.contents;
        this.items.forEach(item -> {ingredients.add(item);});
    }


    public class Crucible_Steel extends Crucibles {
        public Crucible_Steel(Properties props, ItemContainer container) {
            super(props, container);
            ItemStack stack1 = new ItemStack(Items.IRON_INGOT);
            ItemStack stack2 = new ItemStack(Items.COAL);
        }
    }
}
