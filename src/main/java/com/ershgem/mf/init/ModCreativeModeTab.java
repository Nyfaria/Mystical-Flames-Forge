package com.ershgem.mf.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab 
{
    public static final CreativeModeTab MF_ITEMS = new CreativeModeTab("mysticalflamesitems") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BONESTEEL_INGOT.get());
        }
    };

    public static final CreativeModeTab MF_TOOLS = new CreativeModeTab("mysticalflamestools") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BONESTEEL_SWORD.get());
        }
    };

    public static final CreativeModeTab MF_BLOCKS = new CreativeModeTab("mysticalflamesblocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BONESTEEL_BLOCK.get());
        }
    };

    public static final CreativeModeTab MF_ARMOR = new CreativeModeTab("mysticalflamesarmor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BONESTEEL_CHESTPLATE.get());
        }
    };

    public static final CreativeModeTab MF_EGGS = new CreativeModeTab("mysticalflameseggs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.GEM_EGG.get());
        }
    };

    public static final CreativeModeTab MF_DRAGONSCALES = new CreativeModeTab("mysticalflamesscales") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GEM_SCALE.get());
        }
    };

    public static final CreativeModeTab MF_SPAWN_EGGS = new CreativeModeTab("mysticalflamesspawneggs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GEM_SPAWN.get());
        }
    };

    public static final CreativeModeTab MF_DECO = new CreativeModeTab("mysticalflamesdeco") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.GEM_HEAD_BIRCH.get());
        }
    };


}