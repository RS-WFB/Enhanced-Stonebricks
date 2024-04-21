package net.rswfb.enhancedstonebricks.data.loot;

import net.neoforged.fml.common.Mod;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SteelBlock.get());
        this.dropSelf(ModBlocks.SteelStonebrick.get());
        this.dropSelf(ModBlocks.CarbonizedStonebrick.get());
        this.dropSelf(ModBlocks.LightningStruckLog.get());
        this.dropOther(ModBlocks.IronStonebrick.get(), ModItems.RUBY.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(blockDeferredHolder -> blockDeferredHolder.get()).collect(Collectors.toList());
    }
}