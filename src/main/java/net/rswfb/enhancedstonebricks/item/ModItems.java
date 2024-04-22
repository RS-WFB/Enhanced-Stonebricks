package net.rswfb.enhancedstonebricks.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.item.custom.ModArmorMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
	public static final List<Supplier<Item>> ITEMS_SUPPLIER = new ArrayList<>();
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(Registries.ITEM, EnhancedStonebricks.MODID);
	// items
	public static final Supplier<Item> RUBY = register("ruby", () -> new Item(new Item.Properties()));

	public static final Supplier<Item> STEEL_INGOT = register("steel_ingot", () -> new Item(new Item.Properties()));

	// tools & armors
	public static final Supplier<Item> STEEL_HELMET = register("steel_helmet", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final Supplier<Item> STEEL_CHESTPLATE = register("steel_chestplate", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final Supplier<Item> STEEL_LEGGINGS = register("steel_leggings", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final Supplier<Item> STEEL_BOOTS = register("steel_boots", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.BOOTS, (new Item.Properties())));
	// blocks
	public static final Supplier<Item> IRON_STONEBRICK = register("iron_stonebrick", () -> new BlockItem(ModBlocks.IronStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> CARBONIZED_STONEBRICK = register("carbonized_stonebrick", () -> new BlockItem(ModBlocks.CarbonizedStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> STEEL_STONEBRICK = register("steel_stonebrick", () -> new BlockItem(ModBlocks.SteelStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> STEEL_BLOCK = register("steel_block", () -> new BlockItem(ModBlocks.SteelBlock.get(), new Item.Properties()));
	public static final Supplier<Item> KING_STONEBRICK = register("king_stonebrick", () -> new BlockItem(ModBlocks.KingStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> QUEEN_STONEBRICK = register("queen_stonebrick", () -> new BlockItem(ModBlocks.QueenStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> COURTIER_STONEBRICK = register("courtier_stonebrick", () -> new BlockItem(ModBlocks.CourtierStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> LIGHTNINGSTRUCK_LOG = register("lightningstruck_log", () -> new BlockItem(ModBlocks.LightningStruckLog.get(), new Item.Properties()));
	public static Supplier<Item> register(String name, Supplier<Item> supplier){
        Supplier<Item> supplierItem =  ITEMS.register(name,supplier);
        ITEMS_SUPPLIER.add(supplierItem);
        return supplierItem;
    }
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);

	}
}
