package net.rswfb.enhancedstonebricks.event.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.item.ModItems;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class PropertyRegistry {
    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ModItems.SOUL_BOW.get(),new ResourceLocation(ExampleMod.MODID,"power"),(itemStack, level, livingEntity, int_num)->{
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
                }
            });
            ItemProperties.register(ModItems.SOUL_BOW.get(),new ResourceLocation(ExampleMod.MODID,"pulling"),(itemstack, level, livingEntity, int_num)->{
                return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemstack ? 1.0F : 0.0F;
            });
            ;
        });
    }


}