package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.TestItemModel(getResourceLocation(ModItems.TEST_ITEM.get()));
        this.AetherModel(getResourceLocation(ModItems.AETHER.get()));
        this.BadgeModel(getResourceLocation(ModItems.BADGE.get()));
        this.AetherBadgeModel(getResourceLocation(ModItems.AETHER_BADGE.get()));
        this.ElementalCoreModel(getResourceLocation(ModItems.ELEMENTAL_CORE.get()));
        this.CoreOfDesertModel(getResourceLocation(ModItems.CORE_OF_DESERT.get()));
        this.CoreOfDestructionModel(getResourceLocation(ModItems.CORE_OF_DESTRUCTION.get()));
        this.CoreOfEndModel(getResourceLocation(ModItems.CORE_OF_END.get()));
        this.CoreOfNetherModel(getResourceLocation(ModItems.CORE_OF_NETHER.get()));
        this.CoreOfOceanModel(getResourceLocation(ModItems.CORE_OF_OCEAN.get()));
        this.CoreOfRelicModel(getResourceLocation(ModItems.CORE_OF_RELIC.get()));
        this.CoreOfSoulModel(getResourceLocation(ModItems.CORE_OF_SOUL.get()));
        this.CoreOfVitalityModel(getResourceLocation(ModItems.CORE_OF_VITALITY.get()));
        this.SteelIngotModel(getResourceLocation(ModItems.STEEL_INGOT.get()));
        this.SteelSwordModel(getResourceLocation(ModItems.STEEL_SWORD.get()));
        this.SteelPickaxeModel(getResourceLocation(ModItems.STEEL_PICKAXE.get()));

        this.StormIngotModel(getResourceLocation(ModItems.STORM_INGOT.get()));
        this.AstralIngotModel(getResourceLocation(ModItems.ASTRAL_INGOT.get()));

        this.SteelHelmetModel(getResourceLocation(ModItems.STEEL_HELMET.get()));
        this.SteelChestplateModel(getResourceLocation(ModItems.STEEL_CHESTPLATE.get()));
        this.SteelLeggingsModel(getResourceLocation(ModItems.STEEL_LEGGINGS.get()));
        this.SteelBootsModel(getResourceLocation(ModItems.STEEL_BOOTS.get()));
        this.StormStriderHelmetModel(getResourceLocation(ModItems.STORMSTRIDER_HELMET.get()));
        this.StormStriderChestplateModel(getResourceLocation(ModItems.STORMSTRIDER_CHESTPLATE.get()));
        this.StormStriderLeggingsModel(getResourceLocation(ModItems.STORMSTRIDER_LEGGINGS.get()));
        this.StormStriderBootsModel(getResourceLocation(ModItems.STORMSTRIDER_BOOTS.get()));

        this.ArmorPackageModel(getResourceLocation(ModItems.ARMOR_PACKAGE.get()));
        this.EmptyArmorPackageModel(getResourceLocation(ModItems.EMPTY_ARMOR_PACKAGE.get()));
    }


    public ResourceLocation getResourceLocation(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
    }
    public void TestItemModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/test_item"));
    }
    public void SteelIngotModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_ingot"));
    }
    public void StormIngotModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/storm_ingot"));
    }
    public void AstralIngotModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/astral_ingot"));
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
    public void StormStriderHelmetModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/stormstrider_helmet"));
    }
    public void StormStriderChestplateModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/stormstrider_chestplate"));
    }
    public void StormStriderLeggingsModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/stormstrider_leggings"));
    }
    public void StormStriderBootsModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/stormstrider_boots"));
    }
    public void AetherModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/aether"));
    }
    public void BadgeModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/badge"));
    }
    public void AetherBadgeModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/aether_badge"));
    }
    public void ElementalCoreModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/elemental_core"));
    }
    public void CoreOfDesertModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_desert"));
    }
    public void CoreOfDestructionModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_destruction"));
    }
    public void CoreOfEndModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_end"));
    }
    public void CoreOfNetherModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_nether"));
    }
    public void CoreOfOceanModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_ocean"));
    }
    public void CoreOfRelicModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_relic"));
    }
    public void CoreOfSoulModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_soul"));
    }
    public void CoreOfVitalityModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/core_of_vitality"));
    }
    public void SteelSwordModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_sword"));
    }
    public void SteelPickaxeModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/steel_pickaxe"));
    }
    public void ArmorPackageModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/armor_package"));
    }
    public void EmptyArmorPackageModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation("enhancedstonebricks:item/empty_armor_package"));
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
    public void GoldStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/gold_stonebrick"));
    }
    public void AetherStonebrickModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/aether_stonebrick"));
    }
    public void LightningStruckLogModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/lightningstruck_log"));
    }
    public void DesertRelicBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/desert_relic_block"));
    }
    public void EndRelicBLockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/end_relic_block"));
    }
    public void NetherRelicBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/nether_relic_block"));
    }
    public void OceanRelicBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/ocean_relic_block"));
    }
    public void SoulRelicBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/soul_relic_block"));
    }
    public void VitalityRelicBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/vitality_relic_block"));
    }
    public void StonebrickPortalBlockModel(ResourceLocation item){
        this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("block/stonebrick_portal_block_def"));
    }


}