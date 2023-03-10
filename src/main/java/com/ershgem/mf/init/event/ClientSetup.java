package com.ershgem.mf.init.event;

import com.ershgem.mf.client.event.DragonCameraEvent;
import com.ershgem.mf.init.ModBlocks;
import com.ershgem.mf.init.ModKeybinds;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void clientSetup(FMLClientSetupEvent event) {
        //Eggs
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_EGG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DRAMON_EGG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_EGG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PHOENIX_EGG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.QUEENS_CROWN_EGG.get(), RenderType.cutout());

        //Heads
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_BIRCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_OAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_DARK_OAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_ACACIA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_JUNGLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_SPRUCE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_WARPED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GEM_HEAD_CRIMSON.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_OAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_BIRCH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_SPRUCE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_DARKOAK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_JUNGLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_ACACIA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_WARPED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HYDROPTERA_HEAD_CRIMSON.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WOODCUTTER.get(), RenderType.cutout());

        //Hatcheries
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.EARTH_HATCHERY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAVA_HATCHERY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_HATCHERY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_HATCHERY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AIR_HATCHERY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WATER_HATCHERY.get(), RenderType.cutout());


        //Keybinds
        ModKeybinds.init();
        MinecraftForge.EVENT_BUS.register(DragonCameraEvent.class);
    }
}
