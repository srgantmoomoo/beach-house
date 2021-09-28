package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.event.Event;
import net.minecraft.client.util.math.MatrixStack;

// posted in MixinInGameHud
public class EventRender2d extends Event<EventRender2d> {

    public MatrixStack matrix;

    public EventRender2d(MatrixStack matrix) {
        this.matrix = matrix;
    }
}