package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.EventRender2d;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.beachhouse.gui.chat.ChatScreenRenderer;
import me.srgantmoomoo.beachhouse.gui.hud.HudScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(InGameHud.class)
public class MixinInGameHud {

	@Inject(at = @At(value = "RETURN"), method = "render", cancellable = true)
	public void render(MatrixStack matrixStack, float float_1, CallbackInfo info) {

		// renders hud modules when not in the hud screen.
		if(!(Reference.minecraft.currentScreen instanceof HudScreen))
			Main.INSTANCE.hudManager.renderMods(matrixStack);

		// renders the chat outline for commands.
		ChatScreenRenderer.renderChatBox(matrixStack);

		EventRender2d e = new EventRender2d(matrixStack);
		e.setType(Type.PRE);
		ModuleManager.onEvent(e);
		if (e.isCancelled()) info.cancel();
	}

	@Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
	private void renderPortalOverlay(float f, CallbackInfo info) {
		if(Bedroom.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.portalOverlay.isEnabled())
			info.cancel();
	}

}