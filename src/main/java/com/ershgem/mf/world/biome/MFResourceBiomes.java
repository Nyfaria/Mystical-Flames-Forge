package com.ershgem.mf.world.biome;

import com.ershgem.mf.MysticalFlames;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class MFResourceBiomes {

    public static final ResourceKey<Biome> deadland = register("deadland");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MysticalFlames.MOD_ID, name));
    }

}
