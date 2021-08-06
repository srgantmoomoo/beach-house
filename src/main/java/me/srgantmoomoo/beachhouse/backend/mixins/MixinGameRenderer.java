package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.events.WorldRenderEvent;
import me.srgantmoomoo.bedroom.api.event.Type;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.api.event.events.EventWorldRender;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    public MatrixStack matrixStack;

    @Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
    private void renderHand(MatrixStack matrixStack, Camera camera, float f, CallbackInfo info) {
        WorldRenderEvent e = new WorldRenderEvent(f, matrixStack);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}
