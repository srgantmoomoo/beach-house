package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.world.chunk.light.ChunkSkyLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSkyLightProvider.class)
public class MixinChunkSkyLightProvider {

    @Inject(at = @At("HEAD"), method = "recalculateLevel", cancellable = true)
    private void recalculateLevel(long long_1, long long_2, int int_1, CallbackInfoReturnable<Integer> info) {
        if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.skyLightUpdates.isEnabled()) {
            info.setReturnValue(15);
            info.cancel();
        }
    }

}
