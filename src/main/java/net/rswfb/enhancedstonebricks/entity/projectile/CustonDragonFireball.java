package net.rswfb.enhancedstonebricks.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class CustonDragonFireball extends DragonFireball {
    public CustonDragonFireball(Level pLevel, LivingEntity pOwner, double x, double y, double z) {
        super(pLevel, pOwner, x, y, z);
    }

    @Override
    protected void onHit(HitResult pResult) {
        HitResult.Type hitresult$type = pResult.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult)pResult);
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, pResult.getLocation(), GameEvent.Context.of(this, null));
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult)pResult;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
        if (pResult.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult)pResult).getEntity())) {
            if (!this.level().isClientSide) {
                List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0, 2.0, 4.0));
                AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
                Entity entity = this.getOwner();
                if (entity instanceof LivingEntity) {
                    areaeffectcloud.setOwner((LivingEntity)entity);
                }

                areaeffectcloud.setParticle(ParticleTypes.SOUL_FIRE_FLAME);
                areaeffectcloud.setRadius(3.0F);
                areaeffectcloud.setDuration(100);
                areaeffectcloud.setRadiusPerTick((7.0F - areaeffectcloud.getRadius()) / (float)areaeffectcloud.getDuration());
                areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
                if (!list.isEmpty()) {
                    for(LivingEntity livingentity : list) {
                        double d0 = this.distanceToSqr(livingentity);
                        if (d0 < 16.0) {
                            areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                            this.level().playSound(
                                    null,
                                    livingentity.getX(),
                                    livingentity.getY(),
                                    livingentity.getZ(),
                                    SoundEvents.DRAGON_FIREBALL_EXPLODE,
                                    SoundSource.PLAYERS,
                                    2.0F,
                                    1.0F / (this.level().getRandom().nextFloat() * 0.4F + 1.2F) + 0.8F * 0.5F);
                            break;
                        }
                    }
                }


                this.level().addFreshEntity(areaeffectcloud);
                this.discard();
            }
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SOUL_FIRE_FLAME;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
