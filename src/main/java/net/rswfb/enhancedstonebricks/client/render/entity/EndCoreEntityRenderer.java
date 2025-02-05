package net.rswfb.enhancedstonebricks.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.client.model.entity.CoreModel;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.rswfb.enhancedstonebricks.entity.projectile.CoreEntity;

public class EndCoreEntityRenderer extends CoreEntityRenderer {
    public EndCoreEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(CoreEntity pEntity) {
        return new ResourceLocation(ExampleMod.MODID, "textures/entity/core_of_end.png");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientEventHandler {
        @SubscribeEvent
        public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
            evt.registerLayerDefinition(CoreModel.LAYER_LOCATION, CoreModel::createBodyLayer);
        }
        @SubscribeEvent
        public static void onClientEvent(FMLClientSetupEvent event) {

            event.enqueueWork(()->{
                EntityRenderers.register(ModEntityTypes.END_CORE_ENTITY.get(), EndCoreEntityRenderer::new);
            });
        }
    }
}
