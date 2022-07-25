package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {FireworksSparkParticle.Explosion.class, FireworksSparkParticle.Flash.class})
public class MixinFireworksSparkParticle {

    @Inject(method = "buildGeometry", at = @At("HEAD"), cancellable = true)
    private void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta, CallbackInfo info) {
        if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.fireworks.isEnabled())
            info.cancel();
    }

}