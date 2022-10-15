package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.entity.dragons.gem.GemDragon;
import com.ershgem.mf.entity.dragons.hydroptera.HydropteraDragon;
import com.ershgem.mf.entity.dragons.kelptolux.KelptoluxDragon;
import com.ershgem.mf.entity.dragons.lycan.LycanDragon;
import com.ershgem.mf.entity.dragons.phoenix.PhoenixDragon;
import com.ershgem.mf.entity.dragons.queens_crown.QueensCrownDragon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MysticalFlames.MOD_ID);

    //Dragons
    public static final RegistryObject<EntityType<GemDragon>> GEM_DRAGON =
            ENTITIES.register("gem_dragon", () -> EntityType.Builder.of(GemDragon::new, MobCategory.CREATURE)
            		.sized(1.5f, 1.75f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"gem_dragon").toString()));

    public static final RegistryObject<EntityType<PhoenixDragon>> PHOENIX =
            ENTITIES.register("phoenix", () -> EntityType.Builder.of(PhoenixDragon::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.7f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"gem_dragon").toString()));

    public static final RegistryObject<EntityType<QueensCrownDragon>> QUEENS_CROWN =
            ENTITIES.register("queens_crown", () -> EntityType.Builder.of(QueensCrownDragon::new, MobCategory.CREATURE)
                    .sized(1.5f, 4.5f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"gem_dragon").toString()));

    public static final RegistryObject<EntityType<LycanDragon>> LYCAN =
            ENTITIES.register("lycan", () -> EntityType.Builder.of(LycanDragon::new, MobCategory.CREATURE)
                    .sized(1f, 1.9f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"gem_dragon").toString()));

    public static final RegistryObject<EntityType<KelptoluxDragon>> KELPTOLUX =
            ENTITIES.register("kelptolux", () -> EntityType.Builder.of(KelptoluxDragon::new, MobCategory.CREATURE)
                    .sized(1.2f, 3.0f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"gem_dragon").toString()));

    public static final RegistryObject<EntityType<HydropteraDragon>> HYDROPTERA =
            ENTITIES.register("hydroptera", () -> EntityType.Builder.of(HydropteraDragon::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.4f)
                    .clientTrackingRange(50)
                    .setShouldReceiveVelocityUpdates(false)
                    .fireImmune()
                    .build(new ResourceLocation(MysticalFlames.MOD_ID,"hydroptera").toString()));
}
 