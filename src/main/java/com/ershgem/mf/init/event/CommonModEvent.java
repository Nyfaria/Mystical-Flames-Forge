package com.ershgem.mf.init.event;

import com.ershgem.mf.entity.dragons.*;
import com.ershgem.mf.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
        modid = "mysticalflames",
        bus = Bus.MOD
)
public class CommonModEvent {
    public CommonModEvent() {
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GEM_DRAGON.get(), EntityGem.createAttributes().build());
        event.put(ModEntities.HYDROPTERA.get(), EntityHydroptera.createAttributes().build());
        event.put(ModEntities.PHOENIX.get(), EntityPhoenix.createAttributes().build());
        event.put(ModEntities.QUEENS_CROWN.get(), EntityQueensCrown.createAttributes().build());
        event.put(ModEntities.LYCAN.get(), EntityLycan.createAttributes().build());
        event.put(ModEntities.KELPTOLUX.get(), EntityKelptolux.createAttributes().build());
        event.put(ModEntities.DRAMON.get(), EntityDramon.createAttributes().build());
        event.put(ModEntities.ETERNAL_FLAME.get(), EntityEternalFlame.createAttributes().build());
    }
}
