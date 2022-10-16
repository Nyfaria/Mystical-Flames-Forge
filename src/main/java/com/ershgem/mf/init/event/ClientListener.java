package com.ershgem.mf.init.event;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.client.render.*;
import com.ershgem.mf.init.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MysticalFlames.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener
{

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.GEM_DRAGON.get(), RenderGem::new);
            event.registerEntityRenderer(ModEntities.HYDROPTERA.get(), RenderHydroptera::new);
            event.registerEntityRenderer(ModEntities.PHOENIX.get(), RenderPhoenix::new);
            event.registerEntityRenderer(ModEntities.QUEENS_CROWN.get(), RenderQueensCrown::new);
            event.registerEntityRenderer(ModEntities.LYCAN.get(), RenderLycan::new);
            event.registerEntityRenderer(ModEntities.KELPTOLUX.get(), RenderKelptolux::new);
            event.registerEntityRenderer(ModEntities.DRAMON.get(), RenderDramon::new);

    }

}
