package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.item.ModItems;
import  static net.minecraft.tags.ItemTags.*;

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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BADGE.get())
                .pattern("@!@")
                .pattern("!#!")
                .pattern("@!@")
                .define('#', PLANKS)
                .define('@', Items.STICK)
                .define('!', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AETHER_BADGE.get())
                .pattern("@!@")
                .pattern("!#!")
                .pattern("@!@")
                .define('#', ModItems.BADGE.get())
                .define('!', ModItems.AETHER.get())
                .define('@', Items.DIAMOND)
                .unlockedBy("has_badge",has(ModItems.BADGE.get()))
                .save(pRecipeOutput);
        // Steel Related
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SWORD.get())
                .pattern("#")
                .pattern("#")
                .pattern("@")
                .define('@', Items.STICK)
                .define('#', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_PICKAXE.get())
                .pattern("###")
                .pattern(" @ ")
                .pattern(" @ ")
                .define('@', Items.STICK)
                .define('#',ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
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
        // Stonebricks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_STONEBRICK.get())
                .pattern("!@!")
                .pattern("@#@")
                .pattern("!@!")
                .define('!', Items.LAPIS_LAZULI)
                .define('@', Items.GOLD_INGOT)
                .define('#', ModItems.STEEL_STONEBRICK.get())
                .unlockedBy("has_steel_ingot",has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        // 无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.IRON_STONEBRICK.get())
                .requires(Items.IRON_NUGGET,4)
                .requires(Items.STONE_BRICKS, 1)
                .unlockedBy("has_iron_ingot",has(Items.IRON_NUGGET))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.STEEL_INGOT.get(),9)
                .requires(ModItems.STEEL_BLOCK.get(),1)
                .unlockedBy("has_steel_block",has(ModItems.STEEL_BLOCK.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.AETHER_STONEBRICK.get(),1)
                .requires(ModItems.GOLD_STONEBRICK.get(),1)
                .requires(ModItems.AETHER.get(), 2)
                .unlockedBy("has_Aether",has(ModItems.AETHER.get()))
                .save(pRecipeOutput);
        // 冶炼
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.IronStonebrick.get()),RecipeCategory.MISC,ModItems.CARBONIZED_STONEBRICK.get(),0.3f,100)
                .unlockedBy("has_iron_stonebrick",has(ModBlocks.IronStonebrick.get()))
                .save(pRecipeOutput);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.CORE_OF_DESERT.get(), ModItems.CORE_OF_NETHER.get(),
                                                            ModItems.CORE_OF_END.get(), ModItems.CORE_OF_OCEAN.get(),
                                                            ModItems.CORE_OF_RELIC.get(), ModItems.CORE_OF_SOUL.get(),
                                                            ModItems.CORE_OF_VITALITY.get()),
                        RecipeCategory.MISC,ModItems.CORE_OF_DESTRUCTION.get(),0.3f,100)
                .unlockedBy("has_elemental_core",has(ModItems.ELEMENTAL_CORE.get()))
                .save(pRecipeOutput);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.TEST_ITEM.get()),RecipeCategory.MISC,ModItems.STORM_INGOT.get(),0.3f,200)
                .unlockedBy("has_iron_stonebrick",has(ModBlocks.IronStonebrick.get()))
                .save(pRecipeOutput);

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ModItems.AETHER_BADGE.get()),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.TOOLS,
                ModItems.ELEMENTAL_CORE.get()
        ).unlocks("has_aether_badge", has(ModItems.AETHER_BADGE.get()))
                .save(pRecipeOutput, "elemental_core_smithing");
        // 切割石头
//        SingleItemRecipeBuilder.stonecutting()
    }
}