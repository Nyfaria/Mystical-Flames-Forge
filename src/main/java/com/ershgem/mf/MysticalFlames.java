package com.ershgem.mf;

import com.ershgem.mf.init.*;
import com.ershgem.mf.init.event.ClientSetup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(MysticalFlames.MOD_ID)
public class MysticalFlames
{
    public static final String MOD_ID = "mysticalflames";
    public static final Logger LOGGER = LogManager.getLogger();
    public MysticalFlames() {
        //Misc
        IEventBus eventbus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventbus.addListener(ClientSetup::clientSetup));
        eventbus.addListener(this::setup);

        //Registries
        GeckoLib.initialize();
        ModItems.register(eventbus);
        ModBlocks.register(eventbus);
        ModEntities.register(eventbus);
        ModStructures.register(eventbus);
        ModBiomes.register(eventbus);

        //Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MFConfig.CLIENT);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MFConfig.COMMON, "mysticalflames_common.cfg");
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MFConfig.SERVER, "mysticalflames_server.cfg");
    }
    private void setup(final FMLCommonSetupEvent event) {}
}

//Todo list
//1. Give Dragons proper AI, allowing it to walk and and fly
//2. Implement Dragon Hatching, so players can hatch their dragons, and they'll be automatically tamed
//3. Make dragons harder to tame, unless using an item which allows instant taming
