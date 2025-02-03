package net.rswfb.enhancedstonebricks.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientChatEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.rswfb.enhancedstonebricks.ExampleMod;
import net.rswfb.enhancedstonebricks.client.model.entity.CoreModel;
import net.rswfb.enhancedstonebricks.entity.ModEntityTypes;
import net.rswfb.enhancedstonebricks.entity.projectile.CoreEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CoreEntityRenderer extends EntityRenderer<CoreEntity> {
    private EntityModel<CoreEntity> coremodel;

    public CoreEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        coremodel = new CoreModel(pContext.bakeLayer(CoreModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(CoreEntity pEntity) {
        return new ResourceLocation(ExampleMod.MODID, "textures/entity/core_of_vitality.png");
    }

    @Override
    public void render(CoreEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YN.rotationDegrees(45));
        pPoseStack.translate(0,-1,0);
        VertexConsumer buffer = pBuffer.getBuffer(this.coremodel.renderType(this.getTextureLocation(pEntity)));
        this.coremodel.renderToBuffer(pPoseStack,buffer,pPackedLight, OverlayTexture.NO_OVERLAY,1f,1f,1f,1f);
        pPoseStack.popPose();
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
                EntityRenderers.register(ModEntityTypes.CORE_ENTITY.get(), CoreEntityRenderer::new);
            });
        }
    }
}