package me.srgantmoomoo.beachhouse.api.event.events;

import me.srgantmoomoo.beachhouse.api.event.Event;

public class EventWorldRender extends Event {

	public final float partialTicks;

	public EventWorldRender(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}