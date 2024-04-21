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
        this.add(ModItems.STEEL_INGOT.get(),"Steel Ingot");

        this.add(ModBlocks.IronStonebrick.get(),"Iron Stonebrick");
        this.add(ModBlocks.CarbonizedStonebrick.get(),"Carbonized Stonebrick");
        this.add(ModBlocks.SteelStonebrick.get(),"Steel Stonebrick");
        this.add(ModBlocks.SteelBlock.get(),"Steel Block");

        this.add(ModBlocks.LightningStruckLog.get(),"Lightning Struck Log");
    }
}
