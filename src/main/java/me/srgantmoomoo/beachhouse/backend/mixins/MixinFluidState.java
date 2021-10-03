package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.feature.module.modules.player.PlayerVelocity;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidState.class)
public abstract class MixinFluidState {

    @Shadow public abstract Vec3d getVelocity(BlockView world, BlockPos pos);

    @Inject(method = "getVelocity", at = @At("HEAD"), cancellable = true)
    public void getVelocity(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> infoReturnable) {
        if(Bedroom.moduleManager.isModuleEnabled("player velocity") && PlayerVelocity.INSTANCE.noPush.isEnabled())
            infoReturnable.setReturnValue(Vec3d.ZERO);
    }
}