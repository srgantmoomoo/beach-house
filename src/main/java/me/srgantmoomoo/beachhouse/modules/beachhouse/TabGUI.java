package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;

public class TabGUI extends Module {
	
	public TabGUI() {
		super("tab gui", "tabguiiiii.", 0, Category.BEACHHOUSE);
	}
	
	@Override
	public void onEnable() {
		Main.EVENTBUS.subscribe(listener);
		expanded = true;
	}
	
	@Override
	public void onDisable() {
		Main.EVENTBUS.unsubscribe(listener);
	}
	
	int categoryIndex = 0;
	int moduleIndex = 0;
	boolean expanded;
	List<Module> currentModules = new ArrayList<>();
	
	TextRenderer tr = MinecraftClient.getInstance().textRenderer;

	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		// categories
		InGameHud.fill(e.matrix, 0, 12, 70, 82, 0x80000000);
		InGameHud.fill(e.matrix, 0, (categoryIndex * 14) + 12, 70, (categoryIndex * 14) + 26, 0xff000000);
		int yOffset = 14;
		for(Category c : Category.values()) {
			if(expanded && Category.values()[categoryIndex] == c) {
				tr.drawWithShadow(e.matrix, c.name + " <", 2, yOffset + 1, 0xffffffff);
			}else {
				tr.drawWithShadow(e.matrix, c.name + " >", 2, yOffset + 1, 0xffffffff);
			}
			yOffset += 14;
		}
	});
	
}
