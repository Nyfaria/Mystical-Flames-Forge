package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityLycan;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LycanModel extends AnimatedGeoModel<EntityLycan> {
    @Override
    public ResourceLocation getModelLocation(EntityLycan object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/lycan.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLycan object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/lycan.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityLycan animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/lycan.animation.json");
    }
}
