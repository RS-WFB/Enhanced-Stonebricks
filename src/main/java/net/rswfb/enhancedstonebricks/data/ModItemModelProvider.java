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
    }


    public ResourceLocation getResourceLocation(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
    }
    public void RubyModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0",new ResourceLocation("enhancedstonebricks:item/ruby"));
    }
    public void IronStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/iron_stonebrick"));
    }
    public void CarbonizedStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/carbonized_stonebrick"));
    }
}