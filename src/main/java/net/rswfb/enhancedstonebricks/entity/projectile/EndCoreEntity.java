package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.item.ModItems;

public class EndCoreEntity extends CoreEntity {
    public EndCoreEntity(EntityType<? extends CoreEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.pitem = ModItems.CORE_OF_END.get();
        this.duration = 400;
    }
}

