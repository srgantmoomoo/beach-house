package me.srgantmoomoo.beachhouse.module.modules.movement;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.beachhouse.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.font.TextRenderer;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprinnnt.", GLFW.GLFW_KEY_G, Category.RENDER);
		enabled = true;
	}

	public void onEnable() {
		Main.EVENTBUS.subscribe(listener);
		mc.player.setSprinting(true);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		TextRenderer fr = mc.textRenderer;
		fr.drawWithShadow(e.matrix, "penis gui", 1, 11, 0xffffffff);
	});
	
}
