package net.rswfb.enhancedstonebricks.world.structure;

import net.rswfb.enhancedstonebricks.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.world.structure.structure.*;

import java.util.function.Supplier;

public class ModStructureType<S extends Structure> {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, ExampleMod.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<ModDesertRelicStructure>> MOD_DESERT_RELIC_STRUCTURE = registerType("mod_desert_relic_structure", () -> () -> ModDesertRelicStructure.CODEC);

    public static final DeferredHolder<StructureType<?>, StructureType<ModEndRelicStructure>> MOD_END_RELIC_STRUCTURE = registerType("mod_end_relic_structure", () -> () -> ModEndRelicStructure.CODEC);

    public static final DeferredHolder<StructureType<?>, StructureType<ModNetherRelicStructure>> MOD_NETHER_RELIC_STRUCTURE = registerType("mod_nether_relic_structure", () -> () -> ModNetherRelicStructure.CODEC);

    public static final DeferredHolder<StructureType<?>, StructureType<ModOceanRelicStructure>> MOD_OCEAN_RELIC_STRUCTURE = registerType("mod_ocean_relic_structure", () -> () -> ModOceanRelicStructure.CODEC);

    public static final DeferredHolder<StructureType<?>, StructureType<ModSoulRelicStructure>> MOD_SOUL_RELIC_STRUCTURE = registerType("mod_soul_relic_structure", () -> () -> ModSoulRelicStructure.CODEC);

    public static final DeferredHolder<StructureType<?>, StructureType<ModVitalityRelicStructure>> MOD_VITALITY_RELIC_STRUCTURE = registerType("mod_vitality_relic_structure", () -> () -> ModVitalityRelicStructure.CODEC);

    private static <P extends Structure> DeferredHolder<StructureType<?>, StructureType<P>> registerType(String name, Supplier<StructureType<P>> factory) {
        return STRUCTURE_TYPES.register(name, factory);
    }

    public static void register(IEventBus eventBus){
        STRUCTURE_TYPES.register(eventBus);
    }
}
