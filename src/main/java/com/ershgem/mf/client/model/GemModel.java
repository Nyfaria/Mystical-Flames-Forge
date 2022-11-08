package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityGem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GemModel extends AnimatedGeoModel<EntityGem> {
    @Override
    public ResourceLocation getModelLocation(EntityGem object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/new_gem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGem object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/new_gem.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGem animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/new_gem.animation.json");
    }
}
