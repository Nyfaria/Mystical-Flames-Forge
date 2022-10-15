package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.lycan.LycanDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LycanModel extends AnimatedGeoModel<LycanDragon> {
    @Override
    public ResourceLocation getModelLocation(LycanDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/lycan.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LycanDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/lycan.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LycanDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/lycan.animation.json");
    }
}
