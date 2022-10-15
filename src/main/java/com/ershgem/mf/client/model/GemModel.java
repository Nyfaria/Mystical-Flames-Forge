package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.gem.GemDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GemModel extends AnimatedGeoModel<GemDragon> {
    @Override
    public ResourceLocation getModelLocation(GemDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/new_gem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GemDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/new_gem.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GemDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/new_gem.animation.json");
    }
}
