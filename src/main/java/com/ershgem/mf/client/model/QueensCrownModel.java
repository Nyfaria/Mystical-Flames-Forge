package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityQueensCrown;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QueensCrownModel extends AnimatedGeoModel<EntityQueensCrown> {
    @Override
    public ResourceLocation getModelLocation(EntityQueensCrown object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/queenscrown.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityQueensCrown object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/queenscrown.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityQueensCrown animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/queenscrown.animation.json");
    }
}
