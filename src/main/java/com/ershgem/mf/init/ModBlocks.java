package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.blocks.deco.GemDragonHead;
import com.ershgem.mf.blocks.eggs.*;
import com.ershgem.mf.blocks.hatcheries.EarthHatchery;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MysticalFlames.MOD_ID);

    public static final RegistryObject<Block> GEM_EGG = registerBlock("gem_egg",
            () -> new GemEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);
    public static final RegistryObject<Block> DRAMON_EGG = registerBlock("dramon_egg",
            () -> new DramonEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);
    public static final RegistryObject<Block> HYDROPTERA_EGG = registerBlock("hydroptera_egg",
            () -> new HydropteraEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);
    public static final RegistryObject<Block> PHOENIX_EGG = registerBlock("phoenix_egg",
            () -> new PhoenixEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);
    public static final RegistryObject<Block> QUEENS_CROWN_EGG = registerBlock("queens_crown_egg",
            () -> new QueensCrownEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);

    public static final RegistryObject<Block> KELPTOLUX_EGG = registerBlock("kelptolux_egg",
            () -> new KelptoluxEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);

    public static final RegistryObject<Block> LYCAN_EGG = registerBlock("lycan_egg",
            () -> new LycanEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);
    public static final RegistryObject<Block>  ETERNAL_FLAME_EGG = registerBlock("eternal_flame_egg",
            () -> new EternalFlameEgg(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).noOcclusion()),
            ModCreativeModeTab.MF_EGGS, 1);

    public static final RegistryObject<Block> BONESTEEL_BLOCK = registerBlock("bonesteel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.METAL)),
            ModCreativeModeTab.MF_BLOCKS, 64);

    public static final RegistryObject<Block> RAW_BONESTEEL_BLOCK = registerBlock("raw_bonesteel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.MF_BLOCKS, 64);

    public static final RegistryObject<Block> SCALE_ORE = registerBlock("scale_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f).requiresCorrectToolForDrops()), ModCreativeModeTab.MF_BLOCKS, 64);
    public static final RegistryObject<Block> DEAD_GRASS = registerBlock("dead_grass",
            () -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS)
                    .strength(0.5f).sound(SoundType.GRASS)), ModCreativeModeTab.MF_BLOCKS, 64);
    public static final RegistryObject<Block> BURNT_LOG = registerBlock("burnt_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(1.5f).sound(SoundType.WOOD)), ModCreativeModeTab.MF_BLOCKS, 64);
    public static final RegistryObject<Block> BURNT_PLANKS = registerBlock("burnt_planks",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(1.5f).sound(SoundType.WOOD)), ModCreativeModeTab.MF_BLOCKS, 64);
    public static final RegistryObject<Block> GEM_HEAD_BIRCH = registerBlock("gem_head_birch",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_DARK_OAK = registerBlock("gem_head_dark_oak",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_JUNGLE = registerBlock("gem_head_jungle",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_OAK = registerBlock("gem_head_oak",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_SPRUCE = registerBlock("gem_head_spruce",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);

    public static final RegistryObject<Block> GEM_HEAD_WARPED = registerBlock("gem_head_warped",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_ACACIA = registerBlock("gem_head_acacia",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);
    public static final RegistryObject<Block> GEM_HEAD_CRIMSON = registerBlock("gem_head_crimson",
            () -> new GemDragonHead(BlockBehaviour.Properties.of(Material.STONE).strength(0.8f).sound(SoundType.WOOD).noOcclusion()),
            ModCreativeModeTab.MF_DECO, 1);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab, int stack) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, stack);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab, int stack)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab).stacksTo(stack)));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
