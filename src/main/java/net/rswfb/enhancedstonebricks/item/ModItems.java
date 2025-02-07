package net.rswfb.enhancedstonebricks.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.item.custom.*;
import net.rswfb.enhancedstonebricks.item.custom.Armors.EmptyArmorPackageItem;
import net.rswfb.enhancedstonebricks.item.custom.CoreItems.*;
import net.rswfb.enhancedstonebricks.item.custom.tool.ModArmorMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
	public static final List<Supplier<Item>> ITEMS_SUPPLIER = new ArrayList<>();
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(Registries.ITEM, EnhancedStonebricks.MODID);
	// items

	public static final Supplier<Item> STEEL_INGOT = register("steel_ingot", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> AETHER = register("aether", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> BADGE = register("badge", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> AETHER_BADGE = register("aether_badge", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> ELEMENTAL_CORE = register("elemental_core", () -> new Item(new Item.Properties()));
	public static final Supplier<Item> CORE_OF_DESERT = register("core_of_desert", () -> new DesertCoreItem(new Item.Properties())
			.SetEffect(MobEffects.DIG_SPEED));
	public static final Supplier<Item> CORE_OF_DESTRUCTION = register("core_of_destruction", () -> new DestructionCoreItem(new Item.Properties())
			.SetEffect(MobEffects.DAMAGE_BOOST));
	public static final Supplier<Item> CORE_OF_END = register("core_of_end", () -> new EndCoreItem(new Item.Properties())
			.SetEffect(MobEffects.NIGHT_VISION));
	public static final Supplier<Item> CORE_OF_NETHER = register("core_of_nether", () -> new NetherCoreItem(new Item.Properties())
			.SetEffect(MobEffects.FIRE_RESISTANCE));
	public static final Supplier<Item> CORE_OF_OCEAN = register("core_of_ocean", () -> new OceanCoreItem(new Item.Properties())
			.SetEffect(MobEffects.WATER_BREATHING));
	public static final Supplier<Item> CORE_OF_RELIC = register("core_of_relic", () -> new RelicCoreItem(new Item.Properties())
			.SetEffect(MobEffects.LUCK));
	public static final Supplier<Item> CORE_OF_SOUL = register("core_of_soul", () -> new SoulCoreItem(new Item.Properties())
			.SetEffect(MobEffects.DAMAGE_RESISTANCE));
	public static final Supplier<Item> CORE_OF_VITALITY = register("core_of_vitality", () -> new VitalityCoreItem(new Item.Properties())
			.SetEffect(MobEffects.HEALTH_BOOST));

	// tools
	public static final Supplier<Item> STEEL_SWORD = register("steel_sword", SteelSword::new);
	public static final Supplier<Item> STEEL_PICKAXE = register("steel_pickaxe", SteelPickaxe::new);

	// armors
	public static final Supplier<Item> STEEL_HELMET = register("steel_helmet", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final Supplier<Item> STEEL_CHESTPLATE = register("steel_chestplate", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final Supplier<Item> STEEL_LEGGINGS = register("steel_leggings", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final Supplier<Item> STEEL_BOOTS = register("steel_boots", () -> new ArmorItem(ModArmorMaterial.STEEL, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final Supplier<Item> STORMSTRIDER_HELMET = register("stormstrider_helmet", () -> new ArmorItem(ModArmorMaterial.STORMSTRIDER, ArmorItem.Type.HELMET, new Item.Properties()));
	public static final Supplier<Item> STORMSTRIDER_CHESTPLATE = register("stormstrider_chestplate", () -> new ArmorItem(ModArmorMaterial.STORMSTRIDER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final Supplier<Item> STORMSTRIDER_LEGGINGS = register("stormstrider_leggings", () -> new ArmorItem(ModArmorMaterial.STORMSTRIDER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
	public static final Supplier<Item> STORMSTRIDER_BOOTS = register("stormstrider_boots", () -> new ArmorItem(ModArmorMaterial.STORMSTRIDER, ArmorItem.Type.BOOTS, new Item.Properties()));

	public static final Supplier<Item> ARMOR_PACKAGE = register("armor_package", () -> new ArmorPackageItem(new Item.Properties().stacksTo(1)));
	public static final Supplier<Item> EMPTY_ARMOR_PACKAGE = register("empty_armor_package", () -> new EmptyArmorPackageItem(new Item.Properties().stacksTo(1)));
	// weapons
	public static final Supplier<Item> REQUIEM = register("requiem", Requiem::new);

	// blocks
	public static final Supplier<Item> IRON_STONEBRICK = register("iron_stonebrick", () -> new BlockItem(ModBlocks.IronStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> CARBONIZED_STONEBRICK = register("carbonized_stonebrick", () -> new BlockItem(ModBlocks.CarbonizedStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> STEEL_STONEBRICK = register("steel_stonebrick", () -> new BlockItem(ModBlocks.SteelStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> STEEL_BLOCK = register("steel_block", () -> new BlockItem(ModBlocks.SteelBlock.get(), new Item.Properties()));
	public static final Supplier<Item> KING_STONEBRICK = register("king_stonebrick", () -> new BlockItem(ModBlocks.KingStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> QUEEN_STONEBRICK = register("queen_stonebrick", () -> new BlockItem(ModBlocks.QueenStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> COURTIER_STONEBRICK = register("courtier_stonebrick", () -> new BlockItem(ModBlocks.CourtierStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> AETHER_STONEBRICK = register("aether_stonebrick", () -> new BlockItem(ModBlocks.AetherStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> GOLD_STONEBRICK = register("gold_stonebrick", () -> new BlockItem(ModBlocks.GoldStonebrick.get(), new Item.Properties()));
	public static final Supplier<Item> LIGHTNINGSTRUCK_LOG = register("lightningstruck_log", () -> new BlockItem(ModBlocks.LightningStruckLog.get(), new Item.Properties()));
	public static final Supplier<Item> DESERT_RELIC_BLOCK = register("desert_relic_block", () -> new BlockItem(ModBlocks.DesertRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> END_RELIC_BLOCK = register("end_relic_block", () -> new BlockItem(ModBlocks.EndRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> NETHER_RELIC_BLOCK = register("nether_relic_block", () -> new BlockItem(ModBlocks.NetherRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> OCEAN_RELIC_BLOCK = register("ocean_relic_block", () -> new BlockItem(ModBlocks.OceanRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> SOUL_RELIC_BLOCK = register("soul_relic_block", () -> new BlockItem(ModBlocks.SoulRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> VITALITY_RELIC_BLOCK = register("vitality_relic_block", () -> new BlockItem(ModBlocks.VitalityRelicBlock.get(), new Item.Properties()));
	public static final Supplier<Item> STONEBRICK_PORTAL_BLOCK = register("stonebrick_portal_block_default", () -> new BlockItem(ModBlocks.StonebrickPortalBlock.get(), new Item.Properties()));

	public static Supplier<Item> register(String name, Supplier<Item> supplier){
        Supplier<Item> supplierItem = ITEMS.register(name,supplier);
        ITEMS_SUPPLIER.add(supplierItem);
        return supplierItem;
    }
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
