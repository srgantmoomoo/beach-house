package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.bedroom.api.event.Type;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
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
        EventTick e = new EventTick();
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if (e.isCancelled()) info.cancel();
    }
}
