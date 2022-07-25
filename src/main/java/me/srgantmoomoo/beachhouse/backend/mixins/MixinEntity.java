package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.feature.module.modules.player.PlayerVelocity;
import me.srgantmoomoo.bedroom.Bedroom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class MixinEntity {

    @Shadow public void addVelocity(double deltaX, double deltaY, double deltaZ) {}

    @Redirect(method = "pushAwayFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    private void pushAwayFrom_addVelocity(Entity entity, double deltaX, double deltaY, double deltaZ) {
        if (entity == MinecraftClient.getInstance().player) {
            if(Bedroom.INSTANCE.moduleManager.isModuleEnabled("player velocity") && PlayerVelocity.INSTANCE.noPush.isEnabled())
                addVelocity(0, 0, 0);
        }
    }
}
