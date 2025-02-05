package net.rswfb.enhancedstonebricks.entity;

import io.netty.util.Attribute;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.entity.projectile.*;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ExampleMod.MODID);
    public static final Supplier<EntityType<CoreEntity>> CORE_ENTITY = ENTITY_TYPES.register("core_entity", () -> EntityType.Builder.of(CoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("core_entity"));
    public static final Supplier<EntityType<DesertCoreEntity>> DESERT_CORE_ENTITY = ENTITY_TYPES.register("desert_core_entity", () -> EntityType.Builder.of(DesertCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("desert_core_entity"));
    public static final Supplier<EntityType<DestructionCoreEntity>> DESTRUCTION_CORE_ENTITY = ENTITY_TYPES.register("destruction_core_entity", () -> EntityType.Builder.of(DestructionCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("destruction_core_entity"));
    public static final Supplier<EntityType<EndCoreEntity>> END_CORE_ENTITY = ENTITY_TYPES.register("end_core_entity", () -> EntityType.Builder.of(EndCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("end_core_entity"));
    public static final Supplier<EntityType<NetherCoreEntity>> NETHER_CORE_ENTITY = ENTITY_TYPES.register("nether_core_entity", () -> EntityType.Builder.of(NetherCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("nether_core_entity"));
    public static final Supplier<EntityType<OceanCoreEntity>> OCEAN_CORE_ENTITY = ENTITY_TYPES.register("ocean_core_entity", () -> EntityType.Builder.of(OceanCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("ocean_core_entity"));
    public static final Supplier<EntityType<RelicCoreEntity>> RELIC_CORE_ENTITY = ENTITY_TYPES.register("relic_core_entity", () -> EntityType.Builder.of(RelicCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("relic_core_entity"));
    public static final Supplier<EntityType<SoulCoreEntity>> SOUL_CORE_ENTITY = ENTITY_TYPES.register("soul_core_entity", () -> EntityType.Builder.of(SoulCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("soul_core_entity"));
    public static final Supplier<EntityType<VitalityCoreEntity>> VITALITY_CORE_ENTITY = ENTITY_TYPES.register("vitality_core_entity", () -> EntityType.Builder.of(VitalityCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("vitality_core_entity"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}