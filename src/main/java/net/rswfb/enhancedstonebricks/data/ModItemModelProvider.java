package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.RubyModel(getResourceLocation(ModItems.RUBY.get()));
        this.SteelIngotModel(getResourceLocation(ModItems.STEEL_INGOT.get()));
        this.SteelHelmetModel(getResourceLocation(ModItems.STEEL_HELMET.get()));
        this.SteelChestplateModel(getResourceLocation(ModItems.STEEL_CHESTPLATE.get()));
        this.SteelLeggingsModel(getResourceLocation(ModItems.STEEL_LEGGINGS.get()));
        this.SteelBootsModel(getResourceLocation(ModItems.STEEL_BOOTS.get()));
    }


    public ResourceLocation getResourceLocation(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
    }
    public void RubyModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",new ResourceLocation("enhancedstonebricks:item/ruby"));
    }
    public void SteelIngotModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_ingot"));
    }
    public void SteelHelmetModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_helmet"));
    }
    public void SteelChestplateModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_chestplate"));
    }
    public void SteelLeggingsModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_leggings"));
    }
    public void SteelBootsModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_boots"));
    }
    public void IronStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/iron_stonebrick"));
    }
    public void CarbonizedStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/carbonized_stonebrick"));
    }
    public void SteelStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/steel_stonebrick"));
    }
    public void SteelBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/steel_block"));
    }
    public void KingStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/king_stonebrick"));
    }
    public void QueenStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/queen_stonebrick"));
    }
    public void CourtierStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/courtier_stonebrick"));
    }
    public void LightningStruckLogModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/lightningstruck_log"));
    }

}