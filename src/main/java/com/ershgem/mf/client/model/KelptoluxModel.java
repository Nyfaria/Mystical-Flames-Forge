package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityKelptolux;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KelptoluxModel extends AnimatedGeoModel<EntityKelptolux> {
    @Override
    public ResourceLocation getModelLocation(EntityKelptolux object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/kelptolux.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKelptolux object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/kelptolux.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKelptolux animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/kelptolux.animation.json");
    }
}
