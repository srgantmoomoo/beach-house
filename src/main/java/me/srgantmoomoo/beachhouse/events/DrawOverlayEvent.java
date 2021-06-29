package me.srgantmoomoo.beachhouse.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class DrawOverlayEvent extends Event {

	public MatrixStack matrix;

	public DrawOverlayEvent(MatrixStack matrix) {
		this.matrix = matrix;
	}
}
