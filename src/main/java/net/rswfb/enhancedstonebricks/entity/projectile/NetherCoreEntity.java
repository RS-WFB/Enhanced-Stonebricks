package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.item.ModItems;

public class NetherCoreEntity extends CoreEntity{
    public NetherCoreEntity(EntityType<? extends CoreEntity> pEntityType, Level pLevel) {
            super(pEntityType, pLevel);
            this.pitem = ModItems.CORE_OF_NETHER.get();
            this.duration = 400;
        }
}
