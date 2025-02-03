package net.rswfb.enhancedstonebricks.data.loot;

import com.google.common.eventbus.Subscribe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.rswfb.enhancedstonebricks.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SteelBlock.get());
        this.dropSelf(ModBlocks.IronStonebrick.get());
        this.dropSelf(ModBlocks.SteelStonebrick.get());
        this.dropSelf(ModBlocks.CarbonizedStonebrick.get());
        this.dropSelf(ModBlocks.LightningStruckLog.get());
        this.dropSelf(ModBlocks.KingStonebrick.get());
        this.dropSelf(ModBlocks.QueenStonebrick.get());
        this.dropSelf(ModBlocks.CourtierStonebrick.get());
        this.dropSelf(ModBlocks.GoldStonebrick.get());
        this.dropSelf(ModBlocks.StonebrickPortalBlock.get());

        this.dropOther(ModBlocks.AetherStonebrick.get(), ModItems.AETHER.get());

        add(ModBlocks.DesertRelicBlock.get(), createrelicdrops(ModBlocks.DesertRelicBlock.get(), ModItems.CORE_OF_DESERT.get()));
        add(ModBlocks.EndRelicBlock.get(), createrelicdrops(ModBlocks.EndRelicBlock.get(), ModItems.CORE_OF_END.get()));
        add(ModBlocks.NetherRelicBlock.get(), createrelicdrops(ModBlocks.NetherRelicBlock.get(), ModItems.CORE_OF_NETHER.get()));
        add(ModBlocks.OceanRelicBlock.get(), createrelicdrops(ModBlocks.OceanRelicBlock.get(), ModItems.CORE_OF_OCEAN.get()));
        add(ModBlocks.SoulRelicBlock.get(), createrelicdrops(ModBlocks.SoulRelicBlock.get(), ModItems.CORE_OF_SOUL.get()));
        add(ModBlocks.VitalityRelicBlock.get(), createrelicdrops(ModBlocks.VitalityRelicBlock.get(), ModItems.CORE_OF_VITALITY.get()));
    }

    protected LootTable.Builder createrelicdrops(Block pBlock, Item pItem) {
        return LootTable.lootTable()
                .withPool(
                        this.applyExplosionCondition(
                                pBlock,
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(pItem)).add(LootItem.lootTableItem(pItem))
                                        .add(LootItem.lootTableItem(pItem)).add(LootItem.lootTableItem(pItem))
                                        .add(LootItem.lootTableItem(pItem)).add(LootItem.lootTableItem(pItem))
                                        .add(
                                                LootItem.lootTableItem(ModItems.CORE_OF_RELIC.get())
                                        )
                        )
                );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(blockDeferredHolder -> blockDeferredHolder.get()).collect(Collectors.toList());
    }

}
