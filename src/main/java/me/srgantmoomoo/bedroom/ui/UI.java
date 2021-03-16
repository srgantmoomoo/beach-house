package me.srgantmoomoo.bedroom.ui;

import java.util.Comparator;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public class UI {
	private MinecraftClient mc = MinecraftClient.getInstance();

	public UI() {
		Main.EVENTBUS.subscribe(listener);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		TextRenderer tr = mc.textRenderer;
		tr.drawWithShadow(e.matrix, Main.name + " " + Main.version, 2, 2, 0xffffffff);
		
		int y = 1;
		final int[] counter = { 1 };
		for (Module module : ModuleManager.getModules()) {
			if (module.isEnabled()) {
					tr.drawWithShadow(e.matrix, module.getName(), 2, 10 + y, 0x80808080);
				y += tr.fontHeight;
				counter[0]++;
			}
		}
		ModuleManager.modules.sort(Comparator.comparing(module -> -mc.textRenderer.getWidth(module.getName())));
	});

}