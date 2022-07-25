package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public final class MixinBackgroundRenderer {

    @Inject(method = "applyFog", at = @At("TAIL"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo info) {
        if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.fog.isEnabled()) {
            RenderSystem.setShaderFogStart(998);
            RenderSystem.setShaderFogEnd(999);
        }
    }

}