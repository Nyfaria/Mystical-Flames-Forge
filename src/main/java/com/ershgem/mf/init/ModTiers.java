package com.ershgem.mf.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier BONESTEEL = new ForgeTier(5, 1500, 5f,
            3f, 10, BlockTags.NEEDS_IRON_TOOL,
                () -> Ingredient.of(ModItems.BONESTEEL_INGOT.get()));

    public static final ForgeTier TARRAGON = new ForgeTier(6, 1600, 6f,
            4f, 10, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.BONESTEEL_INGOT.get()));

    public static final ForgeTier SCALEMAIL = new ForgeTier(6, 1650, 6f,
            4f, 10, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.MYSTERY_SCALE.get()));
}
