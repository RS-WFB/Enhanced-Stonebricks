package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    //https://docs.neoforged.net/docs/datagen/server/recipes
    public ModRecipeProvider(PackOutput pPackOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(pPackOutput, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        // 有序合成
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        // Steel Armors
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HELMET.get())
                .pattern("###")
                .pattern("# #")
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHESTPLATE.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_LEGGINGS.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        // 无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.IRON_STONEBRICK.get())
                .requires(Items.IRON_INGOT,1)
                .requires(Items.STONE_BRICKS, 1)
                .unlockedBy("has_iron_ingot",has(Items.IRON_INGOT))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STEEL_INGOT.get(),9)
                .requires(ModItems.STEEL_BLOCK.get(),1)
                .unlockedBy("has_steel_block",has(ModItems.STEEL_BLOCK.get()))
                .save(pRecipeOutput);
        // 冶炼
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.IronStonebrick.get()),RecipeCategory.MISC,ModItems.CARBONIZED_STONEBRICK.get(),0.3f,100)
                .unlockedBy("has_iron_stonebrick",has(ModBlocks.IronStonebrick.get()))
                .save(pRecipeOutput);
        // 切割石头
//        SingleItemRecipeBuilder.stonecutting()
    }
}