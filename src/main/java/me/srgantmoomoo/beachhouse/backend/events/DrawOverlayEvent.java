package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

// posted in MixinInGameHud
public class DrawOverlayEvent extends Event<DrawOverlayEvent> {

    public MatrixStack matrix;

    public DrawOverlayEvent(MatrixStack matrix) {
        this.matrix = matrix;
    }
}