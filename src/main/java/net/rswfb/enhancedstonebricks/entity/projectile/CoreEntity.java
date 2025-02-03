package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.minecraft.world.entity.projectile.Projectile;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.function.Supplier;

public class CoreEntity extends Entity implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(CoreEntity.class, EntityDataSerializers.ITEM_STACK);
    private double tx;
    private double ty;
    private double tz;
    private int life;
    private boolean surviveAfterDeath;
    public Item pitem;
    public int duration;



    public CoreEntity(EntityType<? extends CoreEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void setItem(ItemStack pStack) {
        if (!pStack.is(pitem) || pStack.hasTag()) {
            this.getEntityData().set(DATA_ITEM_STACK, pStack.copyWithCount(1));
        }
    }

    private ItemStack getItemRaw() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? new ItemStack(pitem) : itemstack;
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);
    }

    /**
     * Checks if the entity is in range to render.
     */
    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = this.getBoundingBox().getSize() * 4.0;
        if (Double.isNaN(d0)) {
            d0 = 4.0;
        }

        d0 *= 64.0;
        return pDistance < d0 * d0;
    }

    public void signalTo(BlockPos pPos) {
        double d0 = (double)pPos.getX();
        int i = pPos.getY();
        double d1 = (double)pPos.getZ();
        double d2 = d0 - this.getX();
        double d3 = d1 - this.getZ();
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        if (d4 > 12.0) {
            this.tx = this.getX() + d2 / d4 * 12.0;
            this.tz = this.getZ() + d3 / d4 * 12.0;
            this.ty = this.getY() + 8.0;
        } else {
            this.tx = d0;
            this.ty = (double)i;
            this.tz = d1;
        }

        this.life = 0;
        this.surviveAfterDeath = this.random.nextInt(5) > 0;
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @Override
    public void lerpMotion(double pX, double pY, double pZ) {
        this.setDeltaMovement(pX, pY, pZ);
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double d0 = Math.sqrt(pX * pX + pZ * pZ);
            this.setYRot((float)(Mth.atan2(pX, pZ) * 180.0F / (float)Math.PI));
            this.setXRot((float)(Mth.atan2(pY, d0) * 180.0F / (float)Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }


    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        super.tick();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        double d3 = vec3.horizontalDistance();
        this.setXRot(lerpRotation(this.xRotO, (float)(Mth.atan2(life, d3) * 180.0F / (float)Math.PI)));
        this.setYRot(lerpRotation(this.yRotO, (float)(life * 180.0F / (float)Math.PI)));
        if (!this.level().isClientSide) {
            double d4 = this.tx - d0;
            double d5 = this.tz - d2;
            float f = (float)Math.sqrt(d4 * d4 + d5 * d5);
            float f1 = (float)Mth.atan2(d5, d4);
            double d6 = Mth.lerp(0.0025, d3, (double)f);
            double d7 = vec3.y;
            if (f < 1.0F) {
                d6 *= 0.8;
                d7 *= 0.8;
            }

            int j = this.getY() < this.ty ? 1 : -1;
            vec3 = new Vec3(0, d7 + ((double)j - d7) * 0.015F, 0);
            this.setDeltaMovement(vec3);
        }

        float f2 = 0.25F;
        if (this.isInWater()) {
            for(int i = 0; i < 4; ++i) {
                this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
            }
        } else {
            this.level()
                    .addParticle(
                            ParticleTypes.CRIMSON_SPORE,
                            d0 - vec3.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                            d1 - vec3.y * 0.25 - 0.5,
                            d2 - vec3.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                            vec3.x,
                            vec3.y,
                            vec3.z
                    );
        }

        if (!this.level().isClientSide) {
            this.setPos(d0, d1, d2);
            ++this.life;
            if (this.life > duration && !this.level().isClientSide) {
                this.discard();
                if (this.surviveAfterDeath) {
                    this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getItem()));
                } else {
                    this.playSound(SoundEvents.ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                }
            }
        } else {
            this.setPosRaw(d0, d1, d2);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        ItemStack itemstack = this.getItemRaw();
        if (!itemstack.isEmpty()) {
            pCompound.put("Item", itemstack.save(new CompoundTag()));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        ItemStack itemstack = ItemStack.of(pCompound.getCompound("Item"));
        this.setItem(itemstack);
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    /**
     * Returns {@code true} if it's possible to attack this entity with an item.
     */
    @Override
    public boolean isAttackable() {
        return false;
    }

    protected static float lerpRotation(float pCurrentRotation, float pTargetRotation) {
        while(pTargetRotation - pCurrentRotation < -180.0F) {
            pCurrentRotation -= 360.0F;
        }

        while(pTargetRotation - pCurrentRotation >= 180.0F) {
            pCurrentRotation += 360.0F;
        }

        return Mth.lerp(0.2F, pCurrentRotation, pTargetRotation);
    }
}
