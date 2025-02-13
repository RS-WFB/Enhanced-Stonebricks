package net.rswfb.enhancedstonebricks.data;

import net.minecraft.data.DataProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.minecraft.data.loot.LootTableProvider;

import java.util.List;
import java.util.Collections;

@Mod.EventBusSubscriber(modid = EnhancedStonebricks.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        ExistingFileHelper efh = event.getExistingFileHelper();
        var lp = event.getLookupProvider();

        // 语言文件
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModLanguageProvider>) pOutput -> new ModLanguageProvider(pOutput,EnhancedStonebricks.MODID,"en_us")
        );
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModZhCnLanguageProvider>) pOutput -> new ModZhCnLanguageProvider(pOutput,EnhancedStonebricks.MODID)
        );
        // 物品模型
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModItemModelProvider>) pOutput -> new ModItemModelProvider(pOutput,EnhancedStonebricks.MODID,efh)
        );
        // 方块state
        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ModBlockStateProvider>) pOutput -> new ModBlockStateProvider(pOutput,EnhancedStonebricks.MODID,efh)
        );
        // 配方
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ModRecipeProvider>) pOutput -> new ModRecipeProvider(pOutput,lp)
        );
        // loot table
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<net.rswfb.enhancedstonebricks.data.loot.ModLootTableProvider>)pOutput ->
                        new net.rswfb.enhancedstonebricks.data.loot.ModLootTableProvider(pOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(net.rswfb.enhancedstonebricks.data.loot.ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                        ))
        );
        // tag
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ModBlockTagProvider>) pOutput -> new ModBlockTagProvider(pOutput,lp,EnhancedStonebricks.MODID,efh)
        );
        // world gen
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ModWorldGen>) pOutput -> new ModWorldGen(pOutput,lp));

    }
}
