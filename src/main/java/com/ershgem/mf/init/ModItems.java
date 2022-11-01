package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import com.ershgem.mf.init.items.ModArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MysticalFlames.MOD_ID);

    public static final RegistryObject<Item> BONESTEEL_INGOT = ITEMS.register( "bonesteel_ingot",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> BONESTEEL_NUGGET = ITEMS.register( "bonesteel_nugget",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> RAW_BONESTEEL = ITEMS.register( "raw_bonesteel",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> RAW_TARRAGON = ITEMS.register( "raw_tarragon",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> TARRAGON_INGOT = ITEMS.register( "tarragon_ingot",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));

    public static final RegistryObject<Item> DRAGON_FLESH = ITEMS.register( "dragon_flesh",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));

    public static final RegistryObject<Item> GREEN_CRYSTAL = ITEMS.register( "green_crystal",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> RED_CRYSTAL = ITEMS.register( "red_crystal",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    public static final RegistryObject<Item> BLUE_CRYSTAL = ITEMS.register( "blue_crystal",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));
    
    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> GEM_SPAWN = ITEMS.register("gem_spawn_egg", 
    		() -> new ForgeSpawnEggItem(ModEntities.GEM_DRAGON, 0xFF9EC3, 0xFF6089, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> HYDROPTERA_SPAWN = ITEMS.register("hydroptera_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HYDROPTERA, 0x44eede, 0x8df6b9, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> PHOENIX_SPAWN = ITEMS.register("phoenix_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PHOENIX, 0x6cb1d1, 0xf7dc72, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> QUEENS_CROWN_SPAWN = ITEMS.register("queens_crown_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.QUEENS_CROWN, 0xd5c38e, 0x6a553c, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> LYCAN_SPAWN = ITEMS.register("lycan_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LYCAN, 0x845743, 0xf6e593, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> KELPTOLUX_SPAWN = ITEMS.register("kelptolux_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.KELPTOLUX, 0x4da45e, 0xe78495, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));

    public static final RegistryObject<ForgeSpawnEggItem> DRAMON_SPAWN = ITEMS.register("dramon_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DRAMON, 0xc04545, 0xd5a3a3, new Item.Properties().tab(ModCreativeModeTab.MF_SPAWN_EGGS).stacksTo(64)));


    public static final RegistryObject<Item> GEM_SCALE = ITEMS.register("gem_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> DRAMON_SCALE = ITEMS.register("dramon_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> HYDROPTERA_SCALE = ITEMS.register("hydroptera_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> KELPTOLUX_SCALE = ITEMS.register("kelptolux_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> QUEENS_CROWN_SCALE = ITEMS.register("queens_crown_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> ETERNAL_FLAME_SCALE = ITEMS.register("eternal_flame_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));
    public static final RegistryObject<Item> MYSTERY_SCALE = ITEMS.register("mystery_scale",
            () -> new Item (new Item.Properties().tab(ModCreativeModeTab.MF_DRAGONSCALES)));

    public static final RegistryObject<Item> HUNTER_SWORD = ITEMS.register( "hunter_sword",
            () -> new SwordItem(Tiers.DIAMOND,5,6f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));

    //Hatcheries
    //public static final RegistryObject<Item> EARTH_HATCHERY = block(ModBlocks.EARTH_HATCHERY, ModCreativeModeTab.MF_BLOCKS);

    public static final RegistryObject<Item> BONESTEEL_SWORD = ITEMS.register( "bonesteel_sword",
            () -> new SwordItem(ModTiers.BONESTEEL,5,6f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> BONESTEEL_PICKAXE = ITEMS.register( "bonesteel_pickaxe",
            () -> new PickaxeItem(ModTiers.BONESTEEL,3,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> BONESTEEL_AXE = ITEMS.register( "bonesteel_axe",
            () -> new AxeItem(ModTiers.BONESTEEL,4,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> BONESTEEL_SHOVEL = ITEMS.register( "bonesteel_shovel",
            () -> new ShovelItem(ModTiers.BONESTEEL,2,3f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));

    public static final RegistryObject<Item> BONESTEEL_HOE = ITEMS.register( "bonesteel_hoe",
            () -> new HoeItem(ModTiers.BONESTEEL,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));

    public static final RegistryObject<Item> BONESTEEL_HELMET = ITEMS.register( "bonesteel_helmet",
            () -> new ArmorItem(ModArmorMaterials.BONESTEEL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> BONESTEEL_CHESTPLATE = ITEMS.register( "bonesteel_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BONESTEEL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> BONESTEEL_LEGGINGS = ITEMS.register( "bonesteel_leggings",
            () -> new ArmorItem(ModArmorMaterials.BONESTEEL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> BONESTEEL_BOOTS = ITEMS.register( "bonesteel_boots",
            () -> new ArmorItem(ModArmorMaterials.BONESTEEL, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));

    public static final RegistryObject<Item> TARRAGON_HELMET = ITEMS.register( "tarragon_helmet",
            () -> new ArmorItem(ModArmorMaterials.TARRAGON, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> TARRAGON_CHESTPLATE = ITEMS.register( "tarragon_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TARRAGON, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> TARRAGON_LEGGINGS = ITEMS.register( "tarragon_leggings",
            () -> new ArmorItem(ModArmorMaterials.TARRAGON, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> TARRAGON_BOOTS = ITEMS.register( "tarragon_boots",
            () -> new ArmorItem(ModArmorMaterials.TARRAGON, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));

    public static final RegistryObject<Item> TARRAGON_SWORD = ITEMS.register( "tarragon_sword",
            () -> new SwordItem(ModTiers.TARRAGON,8,7f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> TARRAGON_PICKAXE = ITEMS.register( "tarragon_pickaxe",
            () -> new PickaxeItem(ModTiers.TARRAGON,4,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> TARRAGON_AXE = ITEMS.register( "tarragon_axe",
            () -> new AxeItem(ModTiers.TARRAGON,6,7f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> TARRAGON_SHOVEL = ITEMS.register( "tarragon_shovel",
            () -> new ShovelItem(ModTiers.TARRAGON,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> TARRAGON_HOE = ITEMS.register( "tarragon_hoe",
            () -> new HoeItem(ModTiers.TARRAGON,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));

    public static final RegistryObject<Item> SCALEMAIL_HELMET = ITEMS.register("scalemail_helmet",
            () -> new ArmorItem(ModArmorMaterials.SCALEMAIL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> SCALEMAIL_CHESTPLATE = ITEMS.register("scalemail_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SCALEMAIL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> SCALEMAIL_LEGGINGS = ITEMS.register("scalemail_leggings",
            () -> new ArmorItem(ModArmorMaterials.SCALEMAIL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));
    public static final RegistryObject<Item> SCALEMAIL_BOOTS = ITEMS.register("scalemail_boots",
            () -> new ArmorItem(ModArmorMaterials.SCALEMAIL, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MF_ARMOR)));

    public static final RegistryObject<Item> SCALEMAIL_SWORD = ITEMS.register( "scalemail_sword",
            () -> new SwordItem(ModTiers.SCALEMAIL,8,7f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> SCALEMAIL_PICKAXE = ITEMS.register( "scalemail_pickaxe",
            () -> new PickaxeItem(ModTiers.SCALEMAIL,4,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> SCALEMAIL_AXE = ITEMS.register( "scalemail_axe",
            () -> new AxeItem(ModTiers.SCALEMAIL,6,7f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> SCALEMAIL_SHOVEL = ITEMS.register( "scalemail_shovel",
            () -> new ShovelItem(ModTiers.SCALEMAIL,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));
    public static final RegistryObject<Item> SCALEMAIL_HOE = ITEMS.register( "scalemail_hoe",
            () -> new HoeItem(ModTiers.SCALEMAIL,0,0f,
                    new Item.Properties().tab(ModCreativeModeTab.MF_TOOLS)));

    public static final RegistryObject<Item> SLEEP_STAFF = ITEMS.register("sleep_staff",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MF_ITEMS)));

    public static final RegistryObject<Item> PLUS = ITEMS.register( "plus",
            () -> new Item (new Item.Properties()));
    public static final RegistryObject<Item> EQUAL_SIGN = ITEMS.register( "equal_sign",
            () -> new Item (new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
