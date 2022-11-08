package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityPhoenix;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PhoenixModel extends AnimatedGeoModel<EntityPhoenix> {
    @Override
    public ResourceLocation getModelLocation(EntityPhoenix object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/phoenix.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPhoenix object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/phoenix.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPhoenix animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/phoenix.animation.json");
    }
}
