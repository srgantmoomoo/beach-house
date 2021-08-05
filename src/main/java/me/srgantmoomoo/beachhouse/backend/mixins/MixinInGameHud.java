package me.srgantmoomoo.beachhouse.backend.mixins;

import com.mojang.blaze3d.platform.GlStateManager;
import me.srgantmoomoo.beachhouse.backend.events.EventRender2D;
import me.srgantmoomoo.bedroom.api.event.Type;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class MixinInGameHud {

	@Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
	public void render(MatrixStack matrixStack, float float_1, CallbackInfo info) {
		EventDrawOverlay e = new EventDrawOverlay(matrixStack);
		e.setType(Type.PRE);
		Bedroom.onEvent(e);

		if (e.isCancelled())
			info.cancel();
	}

	/*@Inject(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/scoreboard/Scoreboard.getObjectiveForSlot(I)Lnet/minecraft/scoreboard/ScoreboardObjective;"))
	public void draw(MatrixStack matrixStack, float float_1, CallbackInfo ci) {
		try {
			EventRender2D event = new EventRender2D(matrixStack);
			Bedroom.INSTANCE.EVENTBUS.post(event);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}