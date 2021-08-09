package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class Render3dEvent extends Event<Render3dEvent> {

    public final float partialTicks;
    public MatrixStack matrix;

    public Render3dEvent(float partialTicks, MatrixStack matrix) {
        this.partialTicks = partialTicks;
        this.matrix = matrix;
    }
}