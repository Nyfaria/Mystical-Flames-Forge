package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.phoenix.PhoenixDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PhoenixModel extends AnimatedGeoModel<PhoenixDragon> {
    @Override
    public ResourceLocation getModelLocation(PhoenixDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/phoenix.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PhoenixDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/phoenix.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PhoenixDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/phoenix.animation.json");
    }
}
