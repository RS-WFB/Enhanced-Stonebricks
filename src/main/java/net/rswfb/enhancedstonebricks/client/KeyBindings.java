package net.rswfb.enhancedstonebricks.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.rswfb.enhancedstonebricks.EnhancedStonebricks;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String KEY_CATEGORY_ENHANCEDSTONEBRICKS = "key.category.enhancedstonebricks";
    public static final String KEY_ARMOR_EFFECT = "key.enhancedstonebricks.armor_effect";

    public static final KeyMapping ARMOR_EFFECT = new KeyMapping(KEY_CATEGORY_ENHANCEDSTONEBRICKS, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Q,KEY_ARMOR_EFFECT);
}
