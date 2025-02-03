package net.rswfb.enhancedstonebricks.entity;

import net.rswfb.enhancedstonebricks.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rswfb.enhancedstonebricks.entity.projectile.CoreEntity;
import net.rswfb.enhancedstonebricks.entity.projectile.DestructionCoreEntity;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, ExampleMod.MODID);
    public static final Supplier<EntityType<CoreEntity>> CORE_ENTITY = ENTITY_TYPES.register("core_entity", () -> EntityType.Builder.of(CoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("core_entity"));
    public static final Supplier<EntityType<DestructionCoreEntity>> DESTRUCTION_CORE_ENTITY = ENTITY_TYPES.register("destruction_core_entity", () -> EntityType.Builder.of(DestructionCoreEntity::new, MobCategory.MISC).sized(2, 0.5F).build("destruction_core_entity"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }

}