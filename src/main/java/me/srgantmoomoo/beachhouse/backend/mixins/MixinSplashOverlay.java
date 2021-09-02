package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Custom ultralight splash screen
 */
@Mixin(SplashOverlay.class)
public class MixinSplashOverlay {

    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private ResourceReload reload;
    private boolean closing = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void hookSplashInit(CallbackInfo callbackInfo) {

    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void hookSplashRender(MatrixStack matrix, int mouseX, int mouseY, float delta, CallbackInfo callbackInfo) {
        client.textRenderer.drawWithShadow(matrix, "hey", 1, 1, 0xffffffff);
        Reference.textRenderer.drawWithShadow(matrix, "hey", 1, 10, 0xffffffff);

            if (this.reload.isComplete()) {
                if (this.client.currentScreen != null) {
                    this.client.currentScreen.render(matrix, 0, 0, delta);
                }

                if (!closing) {
                    closing = true;
                        this.client.setOverlay(null);
                }
            }

            callbackInfo.cancel();
    }

}