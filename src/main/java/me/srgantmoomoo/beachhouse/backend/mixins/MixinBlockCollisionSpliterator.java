package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.events.EventBlockShape;
import me.srgantmoomoo.bedroom.event.Type;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockCollisionSpliterator;
import net.minecraft.world.BlockView;

@Mixin(BlockCollisionSpliterator.class)
public class MixinBlockCollisionSpliterator {

    @Redirect(method = "offerBlockShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;"))
    private VoxelShape calculatePushVelocity_getCollisionShape(BlockState blockState, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = blockState.getCollisionShape(world, pos, context);

        EventBlockShape e = new EventBlockShape((BlockState) blockState, pos, shape);
        e.setType(Type.PRE);
        ModuleManager.onEvent(e);
        if(e.isCancelled()) return VoxelShapes.empty();
        return e.getShape();
    }
}
