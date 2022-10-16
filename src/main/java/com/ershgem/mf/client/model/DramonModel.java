package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.dramon.DramonDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DramonModel extends AnimatedGeoModel<DramonDragon> {
    @Override
    public ResourceLocation getModelLocation(DramonDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/dramon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DramonDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/dramon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DramonDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/dramon.animation.json");
    }
}
