package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.hydroptera.HydropteraDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HydropteraModel extends AnimatedGeoModel<HydropteraDragon> {
    @Override
    public ResourceLocation getModelLocation(HydropteraDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/hydroptera.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HydropteraDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/hydroptera.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HydropteraDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/hydroptera.animation.json");
    }
}
