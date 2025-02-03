package net.rswfb.enhancedstonebricks.data;

import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.rswfb.enhancedstonebricks.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {


    public ModLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ModItems.RUBY.get(),"Ruby");
        this.add(ModItems.AETHER.get(), "Aether");
        this.add(ModItems.STEEL_INGOT.get(),"Steel Ingot");
        this.add(ModItems.BADGE.get(),"Badge");
        this.add(ModItems.AETHER_BADGE.get(),"Aether Badge");
        this.add(ModItems.ELEMENTAL_CORE.get(),"Elemental Core");
        this.add(ModItems.CORE_OF_DESERT.get(),"Core of Desert");
        this.add(ModItems.CORE_OF_DESTRUCTION.get(),"Core of Destruction");
        this.add(ModItems.CORE_OF_END.get(),"Core of End");
        this.add(ModItems.CORE_OF_NETHER.get(),"Core of Nether");
        this.add(ModItems.CORE_OF_OCEAN.get(),"Core of Ocean");
        this.add(ModItems.CORE_OF_RELIC.get(),"Core of Relic");
        this.add(ModItems.CORE_OF_SOUL.get(),"Core of Soul");
        this.add(ModItems.CORE_OF_VITALITY.get(),"Core of Vitality");

        this.add(ModItems.STEEL_SWORD.get(),"Steel Sword");
        this.add(ModItems.STEEL_PICKAXE.get(),"Steel Pickaxe");
        this.add(ModItems.STEEL_HELMET.get(),"Steel Helmet");
        this.add(ModItems.STEEL_CHESTPLATE.get(),"Steel Chestplate");
        this.add(ModItems.STEEL_LEGGINGS.get(),"Steel Leggings");
        this.add(ModItems.STEEL_BOOTS.get(),"Steel Boots");

        this.add(ModBlocks.IronStonebrick.get(),"Iron Stonebrick");
        this.add(ModBlocks.CarbonizedStonebrick.get(),"Carbonized Stonebrick");
        this.add(ModBlocks.SteelStonebrick.get(),"Steel Stonebrick");
        this.add(ModBlocks.SteelBlock.get(),"Steel Block");
        this.add(ModBlocks.KingStonebrick.get(),"King Stonebrick");
        this.add(ModBlocks.QueenStonebrick.get(),"Queen Stonebrick");
        this.add(ModBlocks.CourtierStonebrick.get(), "Courtier Stonebrick");
        this.add(ModBlocks.GoldStonebrick.get(), "Gold Stonebrick");
        this.add(ModBlocks.AetherStonebrick.get(),"Aether Stonebrick");

        this.add(ModBlocks.LightningStruckLog.get(),"Lightning Struck Log");

        this.add(ModBlocks.DesertRelicBlock.get(), "Desert Relic Block");
        this.add(ModBlocks.EndRelicBlock.get(), "End Relic Block");
        this.add(ModBlocks.NetherRelicBlock.get(), "Nether Relic Block");
        this.add(ModBlocks.OceanRelicBlock.get(), "Ocean Relic Block");
        this.add(ModBlocks.SoulRelicBlock.get(), "Soul Relic Block");
        this.add(ModBlocks.VitalityRelicBlock.get(), "Vitality Relic Block");

        this.add(ModBlocks.StonebrickPortalBlock.get(), "Stonebrick Portal Block");
    }
}
