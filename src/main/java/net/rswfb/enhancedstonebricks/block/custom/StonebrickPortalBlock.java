package net.rswfb.enhancedstonebricks.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.rswfb.enhancedstonebricks.item.ModItems;

import javax.annotation.Nullable;

public class StonebrickPortalBlock extends Block {

    public static final IntegerProperty MOD_LIT = IntegerProperty.create("mod_lit", 0, 8);

    public StonebrickPortalBlock(BlockBehaviour.Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(MOD_LIT, 0));
    }

    private static boolean isCore(ItemStack pStack) {
        return pStack.is(ModItems.CORE_OF_DESERT.get()) ||
                pStack.is(ModItems.CORE_OF_DESTRUCTION.get()) ||
                pStack.is(ModItems.CORE_OF_END.get()) ||
                pStack.is(ModItems.CORE_OF_NETHER.get()) ||
                pStack.is(ModItems.CORE_OF_OCEAN.get()) ||
                pStack.is(ModItems.CORE_OF_RELIC.get()) ||
                pStack.is(ModItems.CORE_OF_SOUL.get()) ||
                pStack.is(ModItems.CORE_OF_VITALITY.get());
    }

    private static boolean isChargeable(BlockState pState) {
        return pState.getValue(MOD_LIT) == 0;
    }

    public static void charge(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, int pValue) {
        BlockState blockstate = pState.setValue(MOD_LIT, pValue);
        pLevel.setBlock(pPos, blockstate, 3);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
        pLevel.playSound(
                null,
                (double)pPos.getX() + 0.5,
                (double)pPos.getY() + 0.5,
                (double)pPos.getZ() + 0.5,
                SoundEvents.ENDER_DRAGON_GROWL,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
        );
    }

    public static BlockState extractProduce(Entity pEntity, BlockState pState, Level pLevel, BlockPos pPos) {
        if (!pLevel.isClientSide) {
            int i = pState.getValue(MOD_LIT);
            Vec3 vec3 = Vec3.atLowerCornerWithOffset(pPos, 0.5, 1.01, 0.5).offsetRandom(pLevel.random, 0.7F);
            ItemEntity itementity;
            if (i == 1) {
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_DESERT.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 2){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_DESTRUCTION.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 3){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_END.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 4){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_NETHER.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 5){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_OCEAN.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 6){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_RELIC.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 7){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_SOUL.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            } else if (i == 8){
                itementity = new ItemEntity(pLevel, vec3.x(), vec3.y(), vec3.z(), new ItemStack(ModItems.CORE_OF_VITALITY.get()));
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            };

            BlockState blockstate = pState.setValue(MOD_LIT, 0);
            pLevel.setBlock(pPos, blockstate, 3);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
            pLevel.playSound(
                    null,
                    (double)pPos.getX() + 0.5,
                    (double)pPos.getY() + 0.5,
                    (double)pPos.getZ() + 0.5,
                    SoundEvents.ENDER_DRAGON_GROWL,
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F
            );
        }

        BlockState blockstate = pState.setValue(MOD_LIT, 0);
        return blockstate;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pHand == InteractionHand.MAIN_HAND && !isCore(itemstack) && isCore(pPlayer.getItemInHand(InteractionHand.OFF_HAND))) {
            return InteractionResult.PASS;
        } else if (itemstack.is(ModItems.CORE_OF_DESERT.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 1);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_DESTRUCTION.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 2);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_END.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 3);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_NETHER.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 4);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_OCEAN.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 5);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_RELIC.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 6);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_SOUL.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 7);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (itemstack.is(ModItems.CORE_OF_VITALITY.get()) && isChargeable(pState) && pState.getValue(MOD_LIT) == 0) {
            charge(pPlayer, pLevel, pPos, pState, 8);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else if (pState.getValue(MOD_LIT) > 0 && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            extractProduce(pPlayer, pState, pLevel, pPos);
            pState.setValue(MOD_LIT, 0);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(MOD_LIT);
    }
}