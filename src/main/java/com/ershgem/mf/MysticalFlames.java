package com.ershgem.mf;

import com.ershgem.mf.init.*;
import com.ershgem.mf.init.event.ClientSetup;
import com.ershgem.mf.network.ControlNetwork;
import com.ershgem.mf.world.biome.MFBiomesInit;
import com.ershgem.mf.world.biome.MFDefaultRegion;
import com.ershgem.mf.world.biome.MFSurfaceDataRule;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(MysticalFlames.MOD_ID)
public class MysticalFlames {
    public static final String MOD_ID = "mysticalflames";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registry.BIOME_REGISTRY, MysticalFlames.MOD_ID);

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
        //ModBiomes.register(eventbus);

        BIOME_REGISTER.register(eventbus);

        MFBiomesInit.setup();

        //Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MFConfig.CLIENT);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MFConfig.COMMON, "mysticalflames_common.cfg");
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MFConfig.SERVER, "mysticalflames_server.cfg");
    }

    private void setup(final FMLCommonSetupEvent event) {
        ControlNetwork.init();
        event.enqueueWork(() ->
        {
            Regions.register(new MFDefaultRegion(2));
            // Given we only add two biomes, we should keep our weight relatively low.
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, MFSurfaceDataRule.makeRules());
        });
    }
}

//Todo list
//1. Make dragons harder to tame
//2. Add the Deadlands biome
//3. Add the nests for them, I'll make them once the eggs are in
