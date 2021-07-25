package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class EventRender2D extends Event {

    private MatrixStack matrix;

    public EventRender2D(MatrixStack matrix) {
        this.matrix = matrix;
    }

    public MatrixStack getMatrixStack() {
        return this.matrix;
    }

}