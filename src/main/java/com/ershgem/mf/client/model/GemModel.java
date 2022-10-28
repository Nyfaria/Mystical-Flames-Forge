package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.Gem2;
import com.ershgem.mf.entity.dragons.gem.GemDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GemModel extends AnimatedGeoModel<Gem2> {
    @Override
    public ResourceLocation getModelLocation(Gem2 object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/new_gem.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Gem2 object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/new_gem.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Gem2 animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/new_gem.animation.json");
    }
}
