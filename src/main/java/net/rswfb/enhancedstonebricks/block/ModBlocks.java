package net.rswfb.enhancedstonebricks.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.custom.IronStonebrick;
import net.rswfb.enhancedstonebricks.block.custom.ModFlammableRotatedPillarBlock;

import java.util.function.Supplier;


public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, EnhancedStonebricks.MODID);

	public static final Supplier<Block> IronStonebrick = BLOCKS.register("iron_stonebrick", net.rswfb.enhancedstonebricks.block.custom.CarbonizedStonebrick::new);
	public static final Supplier<Block> CarbonizedStonebrick = BLOCKS.register("carbonized_stonebrick", net.rswfb.enhancedstonebricks.block.custom.CarbonizedStonebrick::new);
	public static final Supplier<Block> SteelStonebrick = BLOCKS.register("steel_stonebrick", net.rswfb.enhancedstonebricks.block.custom.SteelStonebrick::new);
	public static final Supplier<Block> LightningStruckLog = BLOCKS.register("lightningstruck_log", net.rswfb.enhancedstonebricks.block.custom.LightningStruckLog::new);
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
