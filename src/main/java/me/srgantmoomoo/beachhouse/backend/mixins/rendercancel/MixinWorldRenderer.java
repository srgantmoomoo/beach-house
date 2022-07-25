package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.render.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {
    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    private void renderWeather(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo info) {
        if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.weather.isEnabled())
            info.cancel();
    }

    @Inject(method = "tickRainSplashing", at = @At("HEAD"), cancellable = true)
    public void tickRainSplashing(Camera camera, CallbackInfo info) {
        if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.weather.isEnabled())
            info.cancel();
    }

}
