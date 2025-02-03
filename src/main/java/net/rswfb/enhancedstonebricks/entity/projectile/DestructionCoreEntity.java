package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.item.ModItems;

public class DestructionCoreEntity extends CoreEntity {

    public DestructionCoreEntity(EntityType<? extends CoreEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.pitem = ModItems.CORE_OF_DESTRUCTION.get();
        this.duration = 400;
    }
}
