package net.rswfb.enhancedstonebricks.data;

import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

public class ModBlockStateProvider extends BlockStateProvider {
   public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
       super(output, modid, exFileHelper);
   }

   @Override
   protected void registerStatesAndModels() {
       this.simpleBlockWithItem(ModBlocks.IronStonebrick.get(),cubeAll(ModBlocks.IronStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.CarbonizedStonebrick.get(),cubeAll(ModBlocks.CarbonizedStonebrick.get()));
   }
}