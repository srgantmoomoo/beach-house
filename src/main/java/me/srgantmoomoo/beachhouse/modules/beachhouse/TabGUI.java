package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
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
		InGameHud.fill(e.matrix, 1, 12, 66, 86, 0x80E6AB17);
		InGameHud.fill(e.matrix, 2, (categoryIndex * 14) + 13, 65, (categoryIndex * 14) + 24, 0xffF730FB);

		int count = 0;
		for(Category c : Category.values()) {
			String categoryName = c.name;
			if(c.name.equals("miscellaneous")) categoryName = "misc";
			if(c.name.equals("beachhouse")) categoryName = "beach";

			int catL = 1;
			if(categoryName.equals("player")) catL = 17;
			if(categoryName.equals("render")) catL = 16;
			if(categoryName.equals("combat")) catL = 16;
			if(categoryName.equals("movement")) catL = 10;
			if(categoryName.equals("misc")) catL = 22;
			if(categoryName.equals("beach")) catL = 17;

			InGameHud.drawStringWithShadow(e.matrix, MinecraftClient.getInstance().textRenderer, categoryName, catL, 14 + count * 12, 0xffffffff);
			count++;
		}

	});

		/*// modules
		if (expanded) {

			InGameHud.fill(e.matrix, 70, 12, 140, (currentModules.size() * 14) + 12, 0x90000000);
			InGameHud.fill(e.matrix, 70, (moduleIndex * 14) + 12, 140, (moduleIndex * 14) + 26, 0xFF00A9A9);
			int _yOffset = 14;
			for (Module h : currentModules) {
				TextFormatting color;

				if (h.isEnabled())
					color = TextFormatting.WHITE;
				else
					color = TextFormatting.GRAY;

				tr.drawWithShadow(e.matrix, color + h.getName(), 70 + 2, _yOffset + 1, 0xffffffff);
				_yOffset += 14;
			}
		}

	});*/

}
