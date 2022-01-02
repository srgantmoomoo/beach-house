package me.srgantmoomoo.beachhouse.feature.module.modules.player;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;

public class FastPlace extends Module {

    public FastPlace() {
        super("fast place", "fastplace", "lets u place fast", 0, Category.PLAYER);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            minecraft.itemUseCooldown = 0;
        }
    }

}
