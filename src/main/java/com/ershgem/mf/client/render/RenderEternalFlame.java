package com.ershgem.mf.client.render;

import com.ershgem.mf.client.model.EternalFlameModel;
import com.ershgem.mf.entity.dragons.EntityEternalFlame;
import com.ershgem.mf.util.math.MathX;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Optional;

public class RenderEternalFlame extends GeoEntityRenderer<EntityEternalFlame> {
    private static final float DEGREES_TO_RADIANS = 0.017453292519943295F;
    protected float currentBodyPitch;

    public RenderEternalFlame(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EternalFlameModel());
    }

    public static float toRadians(float angdeg) {
        return angdeg * DEGREES_TO_RADIANS;
    }

    @Override
    public RenderType getRenderType(EntityEternalFlame animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if (animatable.isBaby()) {
            stack.scale(0.3F, 0.3F, 0.3F);
        } else {
            //stack.scale(1.2F, 1.2F, 1.2F);
        }
        return RenderType.entityCutoutNoCull(getTextureLocation(animatable));
    }

    protected String getMainBodyBone() {
        return "Chest";
    }

    protected String getLeftEyeBodyBone() {
        return "EyeL";
    }

    protected String getRightEyeBodyBone() {
        return "EyeR";
    }

    /**
     * Get bone also checks if the bone is present
     *
     * @return
     */
    public Optional<GeoBone> getBone(GeoModel model, String boneString) {
        Optional<GeoBone> bone = model.getBone(boneString);
        return bone.isPresent() ? bone : Optional.empty();
    }

    /**
     * This method is used to modify the models of Entities. It gets called between the rotation values thus making it ideal for manipulations that require rotation updates.
     */
    protected void modifyPitch(GeoModel model, EntityEternalFlame dragon, float partialTicks, RenderType type, PoseStack
            matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder,
                               int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        currentBodyPitch = getBone(model, getMainBodyBone()).get().getRotationX();

        // player xRot and geckolib rotations are flipped in negative and positive values
        if (dragon.isFlying()) {
            if (dragon.getControllingPassenger() instanceof Player player) {
                if (dragon.getX() != dragon.xOld || dragon.getY() != dragon.getY() || dragon.getZ() != dragon.zOld) {
                    dragon.setXRot(player.getXRot());
                    dragon.setXRot(MathX.clamp(dragon.getXRot(), -40, 45));
                }
            }
            currentBodyPitch = toRadians(dragon.getXRot());
        } else {
            currentBodyPitch = 0;
        }
        if (!dragon.isGoingUp()) {
            getBone(model, getMainBodyBone()).get().setRotationX(-currentBodyPitch);
        } else {
            getBone(model, getMainBodyBone()).get().setRotationX(toRadians(getMaxRise()));
        }
    }

    protected int getMaxRise() {
        return 30;
    }

    public float smoothStep(float pitchHoverMax, float pitchMoving, float speed) {
        if (speed <= 0) {
            return pitchHoverMax;
        }
        if (speed >= 1) {
            return pitchMoving;
        }
        speed = speed * speed * (3 - 2 * speed);
        return pitchHoverMax * (1 - speed) + pitchMoving * speed;
    }

    /**
     * Used to clamp a given value between a Minimum and Maximum value.
     *
     * @param val The given Value to be clamped.
     * @param min The Minimum value the given value should have.
     * @param max The Maximum value the given value should have.
     * @return A Value based on the given Value, while ensuring its fitting the Minimum to Maximum range.
     */
    private float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}
