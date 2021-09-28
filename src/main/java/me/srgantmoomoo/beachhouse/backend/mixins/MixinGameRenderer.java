package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.events.EventRender3d;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import net.minecraft.client.render.Shader;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    public MatrixStack matrixStack;

    @Shadow @Nullable private static Shader renderTypeGlintDirectShader;

    @Shadow @Nullable private static Shader renderTypeArmorEntityGlintShader;

    @Shadow @Nullable private static Shader renderTypeArmorGlintShader;

    @Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
    private void renderHand(MatrixStack matrixStack, Camera camera, float f, CallbackInfo info) {

        EventRender3d e = new EventRender3d(f, matrixStack);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();

    }
}
