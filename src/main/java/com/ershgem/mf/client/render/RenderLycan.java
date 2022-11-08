package com.ershgem.mf.client.render;

import com.ershgem.mf.client.model.LycanModel;
import com.ershgem.mf.entity.dragons.EntityLycan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderLycan extends GeoEntityRenderer<EntityLycan> {
    public RenderLycan(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LycanModel());
    }

    @Override
    public RenderType getRenderType(EntityLycan animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if (animatable.isBaby()) {
            stack.scale(0.3F, 0.3F, 0.3F);
        } else {
            //stack.scale(1.2F, 1.2F, 1.2F);
        }
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }
}
