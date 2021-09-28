package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class EventRender3d extends Event<EventRender3d> {

    public final float partialTicks;
    public MatrixStack matrix;

    public EventRender3d(float partialTicks, MatrixStack matrix) {
        this.partialTicks = partialTicks;
        this.matrix = matrix;
    }
}