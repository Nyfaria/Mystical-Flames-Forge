package com.ershgem.mf.init.event;

import com.ershgem.mf.entity.dragons.Gem2;
import com.ershgem.mf.entity.dragons.dramon.DramonDragon;
import com.ershgem.mf.entity.dragons.gem.GemDragon;
import com.ershgem.mf.entity.dragons.hydroptera.HydropteraDragon;
import com.ershgem.mf.entity.dragons.kelptolux.KelptoluxDragon;
import com.ershgem.mf.entity.dragons.lycan.LycanDragon;
import com.ershgem.mf.entity.dragons.phoenix.PhoenixDragon;
import com.ershgem.mf.entity.dragons.queens_crown.QueensCrownDragon;
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
        event.put((EntityType)ModEntities.GEM_DRAGON.get(), Gem2.createAttributes().build());
        event.put((EntityType)ModEntities.HYDROPTERA.get(), HydropteraDragon.createAttributes().build());
        event.put((EntityType)ModEntities.PHOENIX.get(), PhoenixDragon.createAttributes().build());
        event.put((EntityType)ModEntities.QUEENS_CROWN.get(), QueensCrownDragon.createAttributes().build());
        event.put((EntityType)ModEntities.LYCAN.get(), LycanDragon.createAttributes().build());
        event.put((EntityType)ModEntities.KELPTOLUX.get(), KelptoluxDragon.createAttributes().build());
        event.put((EntityType)ModEntities.DRAMON.get(), DramonDragon.createAttributes().build());
    }
}
