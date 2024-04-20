package net.rswfb.enhancedstonebricks.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
	public static final List<Supplier<Item>> ITEMS_SUPPLIER = new ArrayList<>();
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(Registries.ITEM, EnhancedStonebricks.MODID);
	// items
	public static final Supplier<Item> RUBY = register("ruby", () -> new Item(new Item.Properties()));
	// blocks
	public static final Supplier<Item> IRON_STONEBRICK = register("iron_stonebrick", () -> new BlockItem(ModBlocks.IronStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> CARBONIZED_STONEBRICK = register("carbonized_stonebrick", () -> new BlockItem(ModBlocks.CarbonizedStonebrick.get(), new Item.Properties()));
	public static Supplier<Item> register(String name, Supplier<Item> supplier){
        Supplier<Item> supplierItem =  ITEMS.register(name,supplier);
        ITEMS_SUPPLIER.add(supplierItem);
        return supplierItem;
    }
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);

	}
}
