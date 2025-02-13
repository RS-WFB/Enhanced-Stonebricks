package net.rswfb.enhancedstonebricks.item.custom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;

public class ItemContainer extends Item {
    private static final String TAG_ITEMS = "Items";
    public static final int MAX_WEIGHT = 64;
    private static final int BUNDLE_IN_BUNDLE_WEIGHT = 4;
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    public Stream<ItemStack> contents;

    public ItemContainer(Properties pProperties) {
        super(pProperties);
    }

    public static float getFullnessDisplay(ItemStack pStack) {
        return (float)getContentWeight(pStack) / 64.0F;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack pStack, Slot pSlot, ClickAction pAction, Player pPlayer) {
        EnhancedStonebricks.LOGGER.info("1");
        if (pStack.getCount() != 1 || pAction != ClickAction.SECONDARY) {
            EnhancedStonebricks.LOGGER.info("2");
            return false;
        } else {
            ItemStack itemstack = pSlot.getItem();
            EnhancedStonebricks.LOGGER.info(String.valueOf(getWeight(itemstack) >= 16));
            EnhancedStonebricks.LOGGER.info(String.valueOf(itemstack.getItem().canFitInsideContainerItems()));

            if (itemstack.isEmpty()) {
                this.playRemoveOneSound(pPlayer);
                removeOne(pStack).ifPresent(p_150740_ -> add(pStack, pSlot.safeInsert(p_150740_)));
            } else if (itemstack.getItem().canFitInsideContainerItems()) {
                EnhancedStonebricks.LOGGER.info("123");
                if (64 - getContentWeight(pStack) >= 16){
                    EnhancedStonebricks.LOGGER.info("456");
                    int i = 16 / getWeight(itemstack);
                    int j = add(pStack, pSlot.safeTake(itemstack.getCount(), i, pPlayer));
                    if (j > 0) {
                        this.playInsertSound(pPlayer);
                    }
                }
            }

            return true;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(
            ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess
    ) {
        if (pStack.getCount() != 1) return false;
        if (pAction == ClickAction.SECONDARY && pSlot.allowModification(pPlayer)) {
            if (pOther.isEmpty()) {
                removeOne(pStack).ifPresent(p_186347_ -> {
                    this.playRemoveOneSound(pPlayer);
                    pAccess.set(p_186347_);
                });
            } else {
                int i = add(pStack, pOther);
                if (i > 0) {
                    this.playInsertSound(pPlayer);
                    pOther.shrink(i);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link Item#useOn(UseOnContext)}.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        ItemStack itemstack1 = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
        ItemStack itemstack2 = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemstack1.getItem() == ModItems.LID.get() && itemstack2.getItem() == ModItems.CRUCIBLE.get()) {
            this.contents = getContents(itemstack2);
            itemstack1.shrink(1);
            pPlayer.setItemInHand(InteractionHand.MAIN_HAND, ((Crucibles) ModItems.CRUCIBLE_WITH_LID.get()).setContainer((ItemContainer) itemstack2.getItem()).getDefaultInstance());
            return InteractionResultHolder.success(itemstack);
        } else {
        if (dropContents(itemstack, pPlayer)) {
            this.playDropContentsSound(pPlayer);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
        }
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return getContentWeight(pStack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.min(1 + 12 * getContentWeight(pStack) / 64, 13);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return BAR_COLOR;
    }

    private static int add(ItemStack pBundleStack, ItemStack pInsertedStack) {
        if (!pInsertedStack.isEmpty() && pInsertedStack.getItem().canFitInsideContainerItems()) {
            CompoundTag compoundtag = pBundleStack.getOrCreateTag();
            if (!compoundtag.contains("Items")) {
                compoundtag.put("Items", new ListTag());
            }

            int i = getContentWeight(pBundleStack);
            int j = getWeight(pInsertedStack);
            int k = Math.min(pInsertedStack.getCount(), (64 - i) / j);
            if (k == 0) {
                return 0;
            } else {
                ListTag listtag = compoundtag.getList("Items", 10);
                Optional<CompoundTag> optional = getMatchingItem(pInsertedStack, listtag);
                if (optional.isPresent()) {
                    CompoundTag compoundtag1 = optional.get();
                    ItemStack itemstack = ItemStack.of(compoundtag1);
                    itemstack.grow(k);
                    itemstack.save(compoundtag1);
                    listtag.remove(compoundtag1);
                    listtag.add(0, compoundtag1);
                } else {
                    ItemStack itemstack1 = pInsertedStack.copyWithCount(k);
                    CompoundTag compoundtag2 = new CompoundTag();
                    itemstack1.save(compoundtag2);
                    listtag.add(0, compoundtag2);
                }

                return k;
            }
        } else {
            return 0;
        }
    }

    static Optional<CompoundTag> getMatchingItem(ItemStack pStack, ListTag pList) {
        return pStack.is(Items.BUNDLE)
                ? Optional.empty()
                : pList.stream()
                .filter(CompoundTag.class::isInstance)
                .map(CompoundTag.class::cast)
                .filter(p_186350_ -> ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), pStack))
                .findFirst();
    }

    static int getWeight(ItemStack pStack) {
        if (pStack.is(Items.BUNDLE)) {
            return 4 + getContentWeight(pStack);
        } else {
            if ((pStack.is(Items.BEEHIVE) || pStack.is(Items.BEE_NEST)) && pStack.hasTag()) {
                CompoundTag compoundtag = BlockItem.getBlockEntityData(pStack);
                if (compoundtag != null && !compoundtag.getList("Bees", 10).isEmpty()) {
                    return 64;
                }
            }

            return 64 / pStack.getMaxStackSize();
        }
    }

    static int getContentWeight(ItemStack pStack) {
        return getContents(pStack).mapToInt(p_186356_ -> getWeight(p_186356_) * p_186356_.getCount()).sum();
    }

    private static Optional<ItemStack> removeOne(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return Optional.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            if (listtag.isEmpty()) {
                return Optional.empty();
            } else {
                int i = 0;
                CompoundTag compoundtag1 = listtag.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    pStack.removeTagKey("Items");
                }

                return Optional.of(itemstack);
            }
        }
    }

    private static boolean dropContents(ItemStack pStack, Player pPlayer) {
        CompoundTag compoundtag = pStack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return false;
        } else {
            if (pPlayer instanceof ServerPlayer) {
                ListTag listtag = compoundtag.getList("Items", 10);

                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    ItemStack itemstack = ItemStack.of(compoundtag1);
                    pPlayer.drop(itemstack, true);
                }
            }

            pStack.removeTagKey("Items");
            return true;
        }
    }

    public static Stream<ItemStack> getContents(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack pStack) {
        NonNullList<ItemStack> nonnulllist = NonNullList.create();
        getContents(pStack).forEach(nonnulllist::add);
        return Optional.of(new BundleTooltip(nonnulllist, getContentWeight(pStack)));
    }

    /**
     * Allows items to add custom lines of information to the mouseover description.
     */
    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(pStack), 64).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity pItemEntity) {
        ItemUtils.onContainerDestroyed(pItemEntity, getContents(pItemEntity.getItem()));
    }

    private void playRemoveOneSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }
}