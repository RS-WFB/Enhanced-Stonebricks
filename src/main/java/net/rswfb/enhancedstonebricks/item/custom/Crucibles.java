package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.rswfb.enhancedstonebricks.item.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static net.rswfb.enhancedstonebricks.item.custom.ItemContainer.*;

public class Crucibles extends Item {
    ItemStack original;
    Stream<ItemStack> items;
    List<ItemStack> ingredients = new ArrayList<>();

    public Crucibles(Properties props) {
        super(props);
    }

    public Crucibles setContainer(ItemContainer container){
        this.original = container.getDefaultInstance();
        ingredients = new ArrayList<>();
        this.items = container.contents;
        this.items.forEach(item -> {
            ingredients.add(item);});
        return this;
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        ItemStack itemstack1 = pPlayer.getOffhandItem();
        if (pUsedHand == InteractionHand.MAIN_HAND && itemstack1.isEmpty()) {
            ItemStack Cruc = ModItems.CRUCIBLE.get().getDefaultInstance();

            ingredients.forEach(ingredient -> {add(Cruc, ingredient);});
            pPlayer.setItemInHand(pUsedHand, Cruc);

            pPlayer.setItemInHand(InteractionHand.OFF_HAND, ModItems.LID.get().getDefaultInstance());
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }

    }


    public static class Crucible_Steel extends Crucibles {
        public Crucible_Steel(Properties props) {
            super(props);
            ItemStack stack1 = new ItemStack(Items.IRON_INGOT);
            ItemStack stack2 = new ItemStack(Items.COAL);
        }
    }
}
