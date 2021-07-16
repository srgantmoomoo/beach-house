package me.srgantmoomoo.beachhouse.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import net.minecraft.client.world.ClientWorld;

@Mixin(ClientWorld.class)
public class MixinClientWorld {

	@Inject(method = "tickEntities", at = @At("HEAD"), cancellable = true)
	public void tickEntities(CallbackInfo info) {
		EventTick event = new EventTick();
		Bedroom.INSTANCE.EVENTBUS.post(event);
		if(event.isCancelled()) info.cancel();
	}
}