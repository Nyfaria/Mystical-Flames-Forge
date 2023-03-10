package com.ershgem.mf.init;

import com.ershgem.mf.MysticalFlames;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class ModKeybinds {
    public static KeyMapping DRAGON_DESCEND;
    public static KeyMapping DRAGON_ASCEND;

    public static void init() {
        DRAGON_DESCEND = registerKey("descend", "key.category." + MysticalFlames.MOD_ID, InputConstants.KEY_Z);
        DRAGON_ASCEND = registerKey("ascend", "key.category." + MysticalFlames.MOD_ID, InputConstants.KEY_SPACE);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        KeyMapping key = new KeyMapping("key." + MysticalFlames.MOD_ID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}