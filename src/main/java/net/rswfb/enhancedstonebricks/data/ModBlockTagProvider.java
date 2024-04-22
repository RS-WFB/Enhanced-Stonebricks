package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import net.rswfb.enhancedstonebricks.block.ModBlocks;

import org.jetbrains.annotations.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // Needs
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.KingStonebrick.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.QueenStonebrick.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SteelBlock.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SteelStonebrick.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.LightningStruckLog.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.CourtierStonebrick.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.IronStonebrick.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.CarbonizedStonebrick.get());
        // Mineable
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SteelBlock.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.SteelStonebrick.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.IronStonebrick.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.CarbonizedStonebrick.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.KingStonebrick.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.QueenStonebrick.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.CourtierStonebrick.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.LightningStruckLog.get());
    }
}
