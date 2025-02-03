package net.rswfb.enhancedstonebricks.world.structure;

import net.rswfb.enhancedstonebricks.ExampleMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureSets {
    public static final ResourceKey<StructureSet> DESERT_RELIC_STRUCTURE_SET = register("mod_desert_relic_structure");

    public static final ResourceKey<StructureSet> END_RELIC_STRUCTURE_SET = register("mod_end_relic_structure");

    public static final ResourceKey<StructureSet> NETHER_RELIC_STRUCTURE_SET = register("mod_nether_relic_structure");

    public static final ResourceKey<StructureSet> OCEAN_RELIC_STRUCTURE_SET = register("mod_ocean_relic_structure");

    public static final ResourceKey<StructureSet> SOUL_RELIC_STRUCTURE_SET = register("mod_soul_relic_structure");

    public static final ResourceKey<StructureSet> VITALITY_RELIC_STRUCTURE_SET = register("mod_vitality_relic_structure");

    public static void bootstap(BootstapContext<StructureSet> pContext) {
        HolderGetter<Structure> holdergetter = pContext.lookup(Registries.STRUCTURE);

        pContext.register(
                ModStructureSets.DESERT_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_DESERT_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 14357619))
        );
        pContext.register(
                ModStructureSets.END_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_END_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 98715357))
        );
        pContext.register(
                ModStructureSets.NETHER_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_NETHER_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 65423147))
        );
        pContext.register(
                ModStructureSets.OCEAN_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_OCEAN_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 15489653))
        );
        pContext.register(
                ModStructureSets.SOUL_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_SOUL_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 78435694))
        );
        pContext.register(
                ModStructureSets.VITALITY_RELIC_STRUCTURE_SET,
                new StructureSet(holdergetter.getOrThrow(ModStructures.MOD_VITALITY_RELIC_STRUCTURE), new RandomSpreadStructurePlacement(28, 8, RandomSpreadType.LINEAR, 87364230))
        );
    }

    private static ResourceKey<StructureSet> register(String pName) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(ExampleMod.MODID,pName));
    }

}