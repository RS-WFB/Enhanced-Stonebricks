package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import java.util.List;

public class ArmorPackageItem extends Item {
    protected ArmorItem helmet, chestplate, leggings, boots;
    protected boolean isEmpty;
    protected ArmorMaterial material;

    public ArmorPackageItem(Properties properties) {
        super(properties);
    }

    public ArmorPackageItem(Properties properties,
                            ArmorItem helmet, ArmorItem chestplate, ArmorItem leggings, ArmorItem boots) {
        super(properties);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        CompoundTag nbt = stack.getOrCreateTag();

        if (nbt.contains("stored_armor")) {
            // 穿戴存储的护甲
            CompoundTag armorNbt = nbt.getCompound("stored_armor");
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack armorStack = ItemStack.of(armorNbt.getCompound(slot.getName()));
                    pPlayer.setItemSlot(slot, armorStack);
                }
            }
            nbt.remove("stored_armor");
            pPlayer.displayClientMessage(Component.literal("护甲已穿戴！"), true);
            pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pHand).getItem(), 20);
            return InteractionResultHolder.success(stack);
        } else {
            // 存储当前护甲
            CompoundTag armorNbt = new CompoundTag();
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                    ItemStack armorStack = pPlayer.getItemBySlot(slot);
                    if (!armorStack.isEmpty()) {
                        armorNbt.put(slot.getName(), armorStack.save(new CompoundTag()));
                        pPlayer.setItemSlot(slot, ItemStack.EMPTY);
                    }
                }
            }
            if (!armorNbt.isEmpty()) {
                nbt.put("stored_armor", armorNbt);
                pPlayer.displayClientMessage(Component.literal("护甲已存储！"), true);
                pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pHand).getItem(), 20);
                return InteractionResultHolder.success(stack);
            } else {
                pPlayer.displayClientMessage(Component.literal("未穿戴护甲！"), true);
                pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pHand).getItem(), 20);
                return InteractionResultHolder.fail(stack);
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null && nbt.contains("stored_armor")) {
            tooltip.add(Component.literal("已存储护甲").withStyle(ChatFormatting.GREEN));
        } else {
            tooltip.add(Component.literal("未存储护甲").withStyle(ChatFormatting.GRAY));
        }
    }
}
