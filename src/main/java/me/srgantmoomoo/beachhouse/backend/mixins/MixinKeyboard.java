package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.TabGui;
import me.srgantmoomoo.bedroom.api.event.Type;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.ModuleManager;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.hud.HudScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info) {

        // calls openChatScreen method which checks if the prefix is pressed for commands.
        Bedroom.commandManager.openChatScreen();

        // opens hud editor screen.
        if(InputUtil.isKeyPressed(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT))
            Reference.minecraft.openScreen(new HudScreen());

        // for module keybinds.
        Bedroom.moduleManager.keyPress(key, scanCode);

        EventKeyPress e = new EventKeyPress(key, scanCode);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        TabGui.INSTANCE.onKeyPressed(e);
        if (e.isCancelled()) info.cancel();

    }
}