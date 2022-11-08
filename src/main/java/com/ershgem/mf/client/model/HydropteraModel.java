package com.ershgem.mf.client.model;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.EntityHydroptera;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HydropteraModel extends AnimatedGeoModel<EntityHydroptera> {
    @Override
    public ResourceLocation getModelLocation(EntityHydroptera object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "geo/hydroptera.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHydroptera object) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "textures/dragons/hydroptera.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityHydroptera animatable) {
        return new ResourceLocation(MysticalFlames.MOD_ID, "animations/hydroptera.animation.json");
    }
}
