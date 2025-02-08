package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.item.ModItems;

public class VitalityCoreEntity extends CoreEntity {
    public VitalityCoreEntity(EntityType<? extends CoreEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.pitem = ModItems.CORE_OF_VITALITY.get();
        this.duration = 400;
    }
}
