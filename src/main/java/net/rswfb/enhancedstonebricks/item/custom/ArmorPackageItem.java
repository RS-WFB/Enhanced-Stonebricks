package net.rswfb.enhancedstonebricks.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import net.rswfb.enhancedstonebricks.item.ModItems;

import static net.rswfb.enhancedstonebricks.event.utils.Funcs.getArmorMaterial;
import static net.rswfb.enhancedstonebricks.event.utils.Funcs.isFullSetOfArmor;

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
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);
        BlockPos blockpos = new BlockPos((int) pPlayer.getX(), (int) pPlayer.getY(3), (int) pPlayer.getZ());

        pPlayer.startUsingItem(pHand);


        if (this.isEmpty) {
            if (!pPlayer.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && !pPlayer.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                    && !pPlayer.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && !pPlayer.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                if (isFullSetOfArmor(pPlayer)) {
                    this.material = getArmorMaterial(pPlayer);
                    pPlayer.getItemBySlot(EquipmentSlot.HEAD).shrink(1);
                    pPlayer.getItemBySlot(EquipmentSlot.CHEST).shrink(1);
                    pPlayer.getItemBySlot(EquipmentSlot.LEGS).shrink(1);
                    pPlayer.getItemBySlot(EquipmentSlot.FEET).shrink(1);
                    ArmorRetreatHandler(pPlayer, pHand, this.material);
                    pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pHand).getItem(), 20);
                }
            }
        } else {
                if (pPlayer.getItemBySlot(EquipmentSlot.HEAD).isEmpty() && pPlayer.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                        && pPlayer.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && pPlayer.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                    pPlayer.setItemSlot(EquipmentSlot.HEAD, ModItems.STEEL_HELMET.get().getDefaultInstance());
                    pPlayer.setItemSlot(EquipmentSlot.CHEST, ModItems.STEEL_CHESTPLATE.get().getDefaultInstance());
                    pPlayer.setItemSlot(EquipmentSlot.LEGS, ModItems.STEEL_LEGGINGS.get().getDefaultInstance());
                    pPlayer.setItemSlot(EquipmentSlot.FEET, ModItems.STEEL_BOOTS.get().getDefaultInstance());
                    pPlayer.setItemInHand(pHand, ModItems.EMPTY_ARMOR_PACKAGE.get().getDefaultInstance());
                    pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pHand).getItem(), 20);
                }
            }

        pPlayer.swing(pHand, true);
        return InteractionResultHolder.success(itemstack);
    }
    private void ArmorRetreatHandler(Player pPlayer, InteractionHand pHand, ArmorMaterial pMaterial) {
        if (pMaterial.getName().equals(ModArmorMaterial.STEEL.getName())){
            pPlayer.setItemInHand(pHand, ModItems.ARMOR_PACKAGE.get().getDefaultInstance());
        } else {
            EnhancedStonebricks.LOGGER.info(pMaterial.getName());
        }
    }
}
