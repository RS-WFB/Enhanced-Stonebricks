package net.rswfb.enhancedstonebricks.data;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rswfb.enhancedstonebricks.block.custom.StonebrickPortalBlock;

public class ModBlockStateProvider extends BlockStateProvider {
   public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
       super(output, modid, exFileHelper);
   }

   @Override
   protected void registerStatesAndModels() {
       this.simpleBlockWithItem(ModBlocks.IronStonebrick.get(),cubeAll(ModBlocks.IronStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.CarbonizedStonebrick.get(),cubeAll(ModBlocks.CarbonizedStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.SteelStonebrick.get(),cubeAll(ModBlocks.SteelStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.SteelBlock.get(),cubeAll(ModBlocks.SteelBlock.get()));
       this.simpleBlockWithItem(ModBlocks.KingStonebrick.get(),cubeAll(ModBlocks.KingStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.QueenStonebrick.get(),cubeAll(ModBlocks.QueenStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.CourtierStonebrick.get(),cubeAll(ModBlocks.CourtierStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.GoldStonebrick.get(),cubeAll(ModBlocks.GoldStonebrick.get()));
       this.simpleBlockWithItem(ModBlocks.AetherStonebrick.get(),cubeAll(ModBlocks.AetherStonebrick.get()));

       this.simpleBlockWithItem(ModBlocks.DesertRelicBlock.get(),cubeAll(ModBlocks.DesertRelicBlock.get()));
       this.simpleBlockWithItem(ModBlocks.EndRelicBlock.get(),cubeAll(ModBlocks.EndRelicBlock.get()));
       this.simpleBlockWithItem(ModBlocks.NetherRelicBlock.get(),cubeAll(ModBlocks.NetherRelicBlock.get()));
       this.simpleBlockWithItem(ModBlocks.OceanRelicBlock.get(),cubeAll(ModBlocks.OceanRelicBlock.get()));
       this.simpleBlockWithItem(ModBlocks.SoulRelicBlock.get(),cubeAll(ModBlocks.SoulRelicBlock.get()));
       this.simpleBlockWithItem(ModBlocks.VitalityRelicBlock.get(),cubeAll(ModBlocks.VitalityRelicBlock.get()));
       this.ModPortalBlock(ModBlocks.StonebrickPortalBlock.get());

       logBlock(((RotatedPillarBlock) ModBlocks.LightningStruckLog.get()));
       simpleBlockItem(ModBlocks.LightningStruckLog.get(),models().withExistingParent("enhancedstonebricks:lightningstruck_log", "minecraft:block/cube_column"));

   }
   public void ModPortalBlock(Block block){
       var block_0 = models().cubeAll("stonebrick_portal_block_default",new ResourceLocation(ExampleMod.MODID,ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_def"));
       var block_1 = models().cubeAll("stonebrick_portal_block_d1",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_d1"));
       var block_2 = models().cubeAll("stonebrick_portal_block_d2",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_d2"));
       var block_3 = models().cubeAll("stonebrick_portal_block_e",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_e"));
       var block_4 = models().cubeAll("stonebrick_portal_block_n",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_n"));
       var block_5 = models().cubeAll("stonebrick_portal_block_o",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_o"));
       var block_6 = models().cubeAll("stonebrick_portal_block_r",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_r"));
       var block_7 = models().cubeAll("stonebrick_portal_block_s",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_s"));
       var block_8 = models().cubeAll("stonebrick_portal_block_v",new ResourceLocation(ExampleMod.MODID, ModelProvider.BLOCK_FOLDER+"/"+"stonebrick_portal_block_v"));
        getVariantBuilder(block)
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 1)
                .modelForState().modelFile(block_1).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 2)
                .modelForState().modelFile(block_2).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 3)
                .modelForState().modelFile(block_3).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 4)
                .modelForState().modelFile(block_4).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 5)
                .modelForState().modelFile(block_5).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 6)
                .modelForState().modelFile(block_6).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 7)
                .modelForState().modelFile(block_7).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT, 8)
                .modelForState().modelFile(block_8).addModel()
                .partialState().with(StonebrickPortalBlock.MOD_LIT,0)
                .modelForState().modelFile(block_0).addModel();
        simpleBlockItem(block,block_0);
   }
}
