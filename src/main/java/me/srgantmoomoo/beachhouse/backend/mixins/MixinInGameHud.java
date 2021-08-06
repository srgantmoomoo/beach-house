package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.bedroom.api.event.Type;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class MixinInGameHud {

	@Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
	public void render(MatrixStack matrixStack, float float_1, CallbackInfo info) {
		Main.inGameUI.draw(matrixStack);

		DrawOverlayEvent e = new DrawOverlayEvent(matrixStack);
		e.setType(Type.PRE);
		ModuleManager.onEvent(e);
		if (e.isCancelled()) info.cancel();
	}

}