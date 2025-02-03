package net.rswfb.enhancedstonebricks.world.structure;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.neoforged.neoforge.common.Tags;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.world.structure.structure.ModDesertRelicStructure;

import java.util.Map;


public class ModStructures {
    public static Structure.StructureSettings structure(
            HolderSet<Biome> pBiomes, Map<MobCategory, StructureSpawnOverride> pSpawnOverrides, GenerationStep.Decoration pStep, TerrainAdjustment pTerrainAdaptation
    ) {
        return new Structure.StructureSettings(pBiomes, pSpawnOverrides, pStep, pTerrainAdaptation);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome> pBiomes, GenerationStep.Decoration pStep, TerrainAdjustment pTerrainAdaptation) {
        return structure(pBiomes, Map.of(), pStep, pTerrainAdaptation);
    }

    private static Structure.StructureSettings structure(HolderSet<Biome > pBiomes, TerrainAdjustment pTerrainAdaptation) {
        return structure(pBiomes, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, pTerrainAdaptation);
    }


    public static final ResourceKey<Structure> MOD_DESERT_RELIC_STRUCTURE = registerKey("mod_desert_relic_structure");
    public static final ResourceKey<Structure> MOD_END_RELIC_STRUCTURE = registerKey("mod_end_relic_structure");
    public static final ResourceKey<Structure> MOD_NETHER_RELIC_STRUCTURE = registerKey("mod_nether_relic_structure");
    public static final ResourceKey<Structure> MOD_OCEAN_RELIC_STRUCTURE = registerKey("mod_ocean_relic_structure");
    public static final ResourceKey<Structure> MOD_SOUL_RELIC_STRUCTURE = registerKey("mod_soul_relic_structure");
    public static final ResourceKey<Structure> MOD_VITALITY_RELIC_STRUCTURE = registerKey("mod_vitality_relic_structure");

    public static ResourceKey<Structure> registerKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(ExampleMod.MODID,name));
    }

    public static void bootstrap(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);

        context.register(
                ModStructures.MOD_DESERT_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(Tags.Biomes.IS_DESERT), TerrainAdjustment.NONE))
        );
        context.register(
                ModStructures.MOD_END_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(Tags.Biomes.IS_COLD_END), TerrainAdjustment.NONE))
        );
        context.register(
                ModStructures.MOD_NETHER_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(Tags.Biomes.IS_DRY_NETHER), TerrainAdjustment.NONE))
        );
        context.register(
                ModStructures.MOD_OCEAN_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(BiomeTags.HAS_RUINED_PORTAL_OCEAN), TerrainAdjustment.NONE))
        );
        context.register(
                ModStructures.MOD_SOUL_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(Tags.Biomes.IS_DRY_NETHER), TerrainAdjustment.NONE))
        );
        context.register(
                ModStructures.MOD_VITALITY_RELIC_STRUCTURE,
                new ModDesertRelicStructure(structure(biomeHolderGetter.getOrThrow(BiomeTags.IS_FOREST), TerrainAdjustment.NONE))
        );

    }
}
