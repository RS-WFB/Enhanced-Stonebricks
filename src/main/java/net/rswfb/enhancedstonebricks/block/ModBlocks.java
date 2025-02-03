package net.rswfb.enhancedstonebricks.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.custom.StonebrickPortalBlock;

import java.util.function.Supplier;


public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, EnhancedStonebricks.MODID);

	public static final Supplier<Block> IronStonebrick = BLOCKS.register("iron_stonebrick", net.rswfb.enhancedstonebricks.block.custom.CarbonizedStonebrick::new);
	public static final Supplier<Block> CarbonizedStonebrick = BLOCKS.register("carbonized_stonebrick", net.rswfb.enhancedstonebricks.block.custom.CarbonizedStonebrick::new);
	public static final Supplier<Block> SteelStonebrick = BLOCKS.register("steel_stonebrick", net.rswfb.enhancedstonebricks.block.custom.SteelStonebrick::new);
	public static final Supplier<Block> LightningStruckLog = BLOCKS.register("lightningstruck_log", net.rswfb.enhancedstonebricks.block.custom.LightningStruckLog::new);
	public static final Supplier<Block> SteelBlock = BLOCKS.register("steel_block", net.rswfb.enhancedstonebricks.block.custom.SteelBlock::new);
	public static final Supplier<Block> KingStonebrick = BLOCKS.register("king_stonebrick", net.rswfb.enhancedstonebricks.block.custom.KingStonebrick::new);
	public static final Supplier<Block> QueenStonebrick = BLOCKS.register("queen_stonebrick", net.rswfb.enhancedstonebricks.block.custom.QueenStonebrick::new);
	public static final Supplier<Block> CourtierStonebrick = BLOCKS.register("courtier_stonebrick", net.rswfb.enhancedstonebricks.block.custom.CourtierStonebrick::new);
	public static final Supplier<Block> GoldStonebrick = BLOCKS.register("gold_stonebrick", net.rswfb.enhancedstonebricks.block.custom.GoldStonebrick::new);
	public static final Supplier<Block> AetherStonebrick = BLOCKS.register("aether_stonebrick", net.rswfb.enhancedstonebricks.block.custom.AetherStonebrick::new);

	public static final Supplier<Block> DesertRelicBlock = BLOCKS.register("desert_relic_block", net.rswfb.enhancedstonebricks.block.custom.DesertRelicBlock::new);
	public static final Supplier<Block> EndRelicBlock = BLOCKS.register("end_relic_block", net.rswfb.enhancedstonebricks.block.custom.EndRelicBlock::new);
	public static final Supplier<Block> NetherRelicBlock = BLOCKS.register("nether_relic_block", net.rswfb.enhancedstonebricks.block.custom.NetherRelicBlock::new);
	public static final Supplier<Block> OceanRelicBlock = BLOCKS.register("ocean_relic_block", net.rswfb.enhancedstonebricks.block.custom.OceanRelicBlock::new);
	public static final Supplier<Block> SoulRelicBlock = BLOCKS.register("soul_relic_block", net.rswfb.enhancedstonebricks.block.custom.SoulRelicBlock::new);
	public static final Supplier<Block> VitalityRelicBlock = BLOCKS.register("vitality_relic_block", net.rswfb.enhancedstonebricks.block.custom.VitalityRelicBlock::new);

	public static final Supplier<Block> StonebrickPortalBlock = BLOCKS.register("stonebrick_portal_block_default",()->new StonebrickPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()));
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
