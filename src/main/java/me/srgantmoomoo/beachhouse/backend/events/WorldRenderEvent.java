package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class WorldRenderEvent extends Event<WorldRenderEvent> {

    public final float partialTicks;
    public MatrixStack matrix;

    public WorldRenderEvent(float partialTicks, MatrixStack matrix) {
        this.partialTicks = partialTicks;
        this.matrix = matrix;
    }
}