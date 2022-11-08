package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MysticalFlames.MOD_ID);
    //Dragons
    public static final RegistryObject<EntityType<EntityGem>> GEM_DRAGON =
            ENTITIES.register("gem_dragon", () -> EntityType.Builder.of(EntityGem::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.75f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "gem_dragon").toString()));
    public static final RegistryObject<EntityType<EntityPhoenix>> PHOENIX =
            ENTITIES.register("phoenix", () -> EntityType.Builder.of(EntityPhoenix::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.7f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "phoenix").toString()));
    public static final RegistryObject<EntityType<EntityQueensCrown>> QUEENS_CROWN =
            ENTITIES.register("queens_crown", () -> EntityType.Builder.of(EntityQueensCrown::new, MobCategory.CREATURE)
                    .sized(1.5f, 4.5f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "queens_crown").toString()));
    public static final RegistryObject<EntityType<EntityLycan>> LYCAN =
            ENTITIES.register("lycan", () -> EntityType.Builder.of(EntityLycan::new, MobCategory.CREATURE)
                    .sized(1f, 1.9f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "lycan").toString()));
    public static final RegistryObject<EntityType<EntityDramon>> DRAMON =
            ENTITIES.register("dramon", () -> EntityType.Builder.of(EntityDramon::new, MobCategory.CREATURE)
                    .sized(1.3f, 2.5f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "dramon").toString()));
    public static final RegistryObject<EntityType<EntityKelptolux>> KELPTOLUX =
            ENTITIES.register("kelptolux", () -> EntityType.Builder.of(EntityKelptolux::new, MobCategory.CREATURE)
                    .sized(1.2f, 3.0f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "kelptolux").toString()));
    public static final RegistryObject<EntityType<EntityHydroptera>> HYDROPTERA =
            ENTITIES.register("hydroptera", () -> EntityType.Builder.of(EntityHydroptera::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.4f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "hydroptera").toString()));
    public static final RegistryObject<EntityType<EntityEternalFlame>> ETERNAL_FLAME =
            ENTITIES.register("eternal_flame", () -> EntityType.Builder.of(EntityEternalFlame::new, MobCategory.CREATURE)
                    .sized(2.3f, 5.6f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID, "eternal_flame").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
 