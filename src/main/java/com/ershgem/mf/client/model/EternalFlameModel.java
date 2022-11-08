package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityEternalFlame;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EternalFlameModel extends AnimatedGeoModel<EntityEternalFlame> {
    @Override
    public ResourceLocation getModelLocation(EntityEternalFlame object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/eternal_flame.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityEternalFlame object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/eternal_flame.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityEternalFlame animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/eternal_flame.animation.json");
    }
}
