package net.rswfb.enhancedstonebricks.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.world.structure.ModStructureSets;
import net.rswfb.enhancedstonebricks.world.structure.ModStructures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap);

    public ModWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ExampleMod.MODID));
    }
}
