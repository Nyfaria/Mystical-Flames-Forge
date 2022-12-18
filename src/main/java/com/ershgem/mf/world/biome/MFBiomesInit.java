package com.ershgem.mf.world.biome;

import com.ershgem.mf.MysticalFlames;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Supplier;

public class MFBiomesInit {

    private static List<ResourceKey<Biome>> overworldBiomes = Lists.newArrayList();
    private static List<ResourceKey<Biome>> allBiomes = Lists.newArrayList();

    public static final ResourceKey<Biome> DEADLANDS = registerOverworld("deadlands");

    public static void setup() {
        registerBiome(DEADLANDS, () -> MFOverworldBiomes.deadlands());
    }


    public static void registerBiome(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier) {
        MysticalFlames.BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
    }


    public static List<ResourceKey<Biome>> getOverworldBiomes() {
        return ImmutableList.copyOf(overworldBiomes);
    }

    public static List<ResourceKey<Biome>> getAllBiomes() {
        return ImmutableList.copyOf(allBiomes);
    }

    private static ResourceKey<Biome> registerOverworld(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MysticalFlames.MOD_ID, name));
        overworldBiomes.add(key);
        allBiomes.add(key);
        return key;
    }

    private static ResourceKey<Biome> register(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MysticalFlames.MOD_ID, name));
        allBiomes.add(key);
        return key;
    }

}
