package net.rswfb.enhancedstonebricks.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class RenderHelper {
    /**
     * 渲染实体的边界框
     * @param poseStack      矩阵堆栈
     * @param buffer         渲染缓冲区
     * @param entityAABB     实体的边界框
     * @param color          颜色（ARGB格式，如 0xFF0000 表示红色）
     * @param lineWidth      线宽（像素）
     */
    public static void renderBoundingBox(
            PoseStack poseStack,
            MultiBufferSource buffer,
            AABB entityAABB,
            int color,
            float lineWidth
    ) {
        // 获取当前相机的坐标（避免渲染偏移）
        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double offsetX = -cameraPos.x;
        double offsetY = -cameraPos.y;
        double offsetZ = -cameraPos.z;

        // 调整边界框坐标（相对相机位置）
        AABB adjustedAABB = entityAABB.move(offsetX, offsetY, offsetZ);

        // 绘制边界框
        LevelRenderer.renderLineBox(
                poseStack,
                buffer.getBuffer(RenderType.lines()),
                adjustedAABB,
                (color >> 16 & 0xFF) / 255.0F, // 红
                (color >> 8 & 0xFF) / 255.0F,  // 绿
                (color & 0xFF) / 255.0F,       // 蓝
                (color >> 24 & 0xFF) / 255.0F  // 透明度
        );
    }
}