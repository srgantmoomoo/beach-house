package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.event.Event;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;

// called in MixinBlockCollisionSpliterator
public class EventBlockShape extends Event<EventBlockShape> {
    private BlockState state;
    private BlockPos pos;
    private VoxelShape shape;

    public EventBlockShape(BlockState state, BlockPos pos, VoxelShape shape) {
        this.state = state;
        this.pos = pos;
        this.setShape(shape);
    }

    public BlockState getState() {
        return state;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public VoxelShape getShape() {
        return shape;
    }

    public void setShape(VoxelShape shape) {
        this.shape = shape;
    }
}