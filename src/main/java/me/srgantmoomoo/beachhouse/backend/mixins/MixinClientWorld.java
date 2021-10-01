package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.NameChecker;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.render.FullBright;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.world.ClientWorld;

@Mixin(ClientWorld.class)
public class MixinClientWorld {

    @Inject(method = "tickEntities", at = @At("HEAD"), cancellable = true)
    public void tickEntities(CallbackInfo info) {
        if(FullBright.goingDown) {
            double dif = (FullBright.originalGamma - Reference.minecraft.options.gamma);
            Reference.minecraft.options.gamma += dif * 0.1f;
            if (Math.abs(dif) <= .05f) {
                Reference.minecraft.options.gamma = FullBright.originalGamma;
                FullBright.goingDown = false;
            }
        }

        EventTick e = new EventTick();
        e.setType(Type.PRE);
        NameChecker.INSTANCE.onEvent(e);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}
