package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.EventGuiKeyPress;
import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.TabGui;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.ModuleManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    // this first event is for keybinds being read in gui's. the second method doesn't read keys that are pressed when they are pressed in a gui.
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        // for clickgui key listener
        if (key >= 0) {
            Main.INSTANCE.clickGuiScreen.onKeyPressed(key);
        }

        // for command line key listener
        Main.INSTANCE.commandLineScreen.onKeyPressed(key);

        // for options screen key listener
        Main.INSTANCE.optionsScreen.onKeyPressed(key);

        EventGuiKeyPress e = new EventGuiKeyPress(key, scanCode);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKey_1(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        // calls openChatScreen method which checks if the prefix is pressed for commands.
        Bedroom.INSTANCE.commandManager.openChatScreen();

        // for module keybinds.
        Bedroom.INSTANCE.moduleManager.keyPress(key, scanCode);

        EventKeyPress e = new EventKeyPress(key, scanCode);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        TabGui.INSTANCE.onKeyPressed(e); // for tab gui key listener (using instance cause tabgui is a module)
        if (e.isCancelled()) info.cancel();
    }
}