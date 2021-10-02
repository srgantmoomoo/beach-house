package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class MixinInGameOverlayRenderer {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void onRenderFireOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo info) {
        if(Bedroom.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.fireOverlay.isEnabled())
            info.cancel();
    }

    @Inject(method = "renderUnderwaterOverlay", at = @At("HEAD"), cancellable = true)
    private static void onRenderUnderwaterOverlay(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo info) {
        if(Bedroom.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.waterOverlay.isEnabled())
            info.cancel();
    }
}
