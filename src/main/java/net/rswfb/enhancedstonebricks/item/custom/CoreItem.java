package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.rswfb.enhancedstonebricks.entity.projectile.CoreEntity;
import net.rswfb.enhancedstonebricks.entity.projectile.DestructionCoreEntity;

import java.util.function.Supplier;

public class CoreItem extends Item {
    private MobEffect effect;
    private Supplier<EntityType<? extends CoreEntity>> sp;

    public CoreItem(Properties properties) {
        super(properties);
    }

    public CoreItem SetEffect(MobEffect pEff){
        effect = pEff;
        return this;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);
        BlockPos blockpos = new BlockPos((int)pPlayer.getX(), (int)pPlayer.getY(3), (int)pPlayer.getZ());

        if (blockhitresult.getType() == HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            MobEffect pEffect = effect;
            MobEffectInstance mobeffectinstance = new MobEffectInstance(pEffect, 400, 2);
            pPlayer.addEffect(mobeffectinstance);

            CoreEntity coreentity = new CoreEntity(ModEntityTypes.CORE_ENTITY.get(), pLevel);
            coreentity.pitem = itemstack.getItem();
            coreentity.duration = 400;
            coreentity.setPos(pPlayer.getX(), pPlayer.getY(3), pPlayer.getZ());
            coreentity.setItem(itemstack);
            coreentity.signalTo(blockpos);

            pLevel.gameEvent(GameEvent.PROJECTILE_SHOOT, coreentity.position(), GameEvent.Context.of(pPlayer));
            pLevel.addFreshEntity(coreentity);

            pLevel.playSound(
                    null,
                    pPlayer.getX(),
                    pPlayer.getY(),
                    pPlayer.getZ(),
                    SoundEvents.ENDER_EYE_LAUNCH,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            pPlayer.swing(pHand, true);
            pPlayer.gameEvent(GameEvent.DRINK);
            return InteractionResultHolder.success(itemstack);
        }
    }
    }
