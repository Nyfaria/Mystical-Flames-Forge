package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.queens_crown.QueensCrownDragon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QueensCrownModel extends AnimatedGeoModel<QueensCrownDragon> {
    @Override
    public ResourceLocation getModelLocation(QueensCrownDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/queenscrown.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(QueensCrownDragon object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/queenscrown.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(QueensCrownDragon animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/queenscrown.animation.json");
    }
}
