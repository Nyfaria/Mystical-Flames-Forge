package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, MysticalFlames.MOD_ID);


    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}