package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.rswfb.enhancedstonebricks.entity.projectile.CoreEntity;
import org.jetbrains.annotations.NotNull;

public class DestructionCoreItem extends CoreItem{
    public DestructionCoreItem(Properties properties) {
        super(properties);
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

            CoreEntity coreentity = new CoreEntity(ModEntityTypes.DESTRUCTION_CORE_ENTITY.get(), pLevel);
            return getItemStackInteractionResultHolder(pLevel, pPlayer, pHand, itemstack, blockpos, coreentity);
        }
    }
}
