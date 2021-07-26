package me.srgantmoomoo.beachhouse.backend.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventWorldRender;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

	@Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
	private void renderHand(MatrixStack matrixStack, Camera camera, float f, CallbackInfo info) {
		EventWorldRender event = new EventWorldRender(f, matrixStack);
		Bedroom.INSTANCE.EVENTBUS.post(event);
		if (event.isCancelled()) info.cancel();
	}
}