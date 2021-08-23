package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.TabGui;
import me.srgantmoomoo.bedroom.api.event.Type;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.ModuleManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.Keyboard;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo callbackInfo) {
        if (key >= 0) {
            ClickGuiScreen.INSTANCE.onKeyPressed(key);
        }
    }

    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKeyEvent_1(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo info) {
        // calls openChatScreen method which checks if the prefix is pressed for commands.
        Bedroom.commandManager.openChatScreen();

        // for module keybinds.
        Bedroom.moduleManager.keyPress(key, scanCode);

        EventKeyPress e = new EventKeyPress(key, scanCode);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        TabGui.INSTANCE.onKeyPressed(e);
        if (e.isCancelled()) info.cancel();
    }
}