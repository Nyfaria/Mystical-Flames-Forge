package com.ershgem.mf.world.biome;

import com.ershgem.mf.init.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class DeadlandsBiome {
    public static final Climate.ParameterPoint PARAMETER_POINT = new Climate.ParameterPoint(Climate.Parameter.span(-0.571428571429f, 0.571428571429f),
            Climate.Parameter.span(-0.571428571429f, 0.571428571429f), Climate.Parameter.span(-0.061428571429f, 1.081428571429f),
            Climate.Parameter.span(0.228571428571f, 1.371428571429f), Climate.Parameter.point(0),
            Climate.Parameter.span(-0.645779774874f, 0.497077367984f), 0);

    public static Biome createBiome() {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder().fogColor(-13421773).waterColor(-10066330).waterFogColor(-13421773)
                .skyColor(-13421773).foliageColorOverride(-10066330).grassColorOverride(-10066330).build();
        BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder();
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                PlacementUtils.register("mysticalflames:tree_deadlands", FeatureUtils.register("mysticalflames:tree_deadlands", Feature.TREE,
                                new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.BURNT_LOG.get().defaultBlockState()),
                                        new MegaJungleTrunkPlacer(18, 2, 19),
                                        BlockStateProvider.simple(ModBlocks.BONESTEEL_BLOCK.get().defaultBlockState()),
                                        new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2))
                                        .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, LeaveVineDecorator.INSTANCE)).build()),
                        List.of(CountPlacement.of(1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
                                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
                                BiomeFilter.biome())));
        biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                PlacementUtils.register("mysticalflames:grass_deadlands", VegetationFeatures.PATCH_GRASS,
                        List.of(NoiseThresholdCountPlacement.of(-0.8D, 5, 4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                                BiomeFilter.biome())));
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
        BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);
        MobSpawnSettings.Builder mobSpawnInfo = new MobSpawnSettings.Builder();
        return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE).temperature(0.5f)
                .downfall(0.5f).specialEffects(effects).mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings.build())
                .build();
    }

    public static void init() {
    }
}

