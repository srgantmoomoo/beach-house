package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.glfw.GLFW;

public class TabGUI extends Module {
	public ModeSetting theme = new ModeSetting("theme", this, "beach", "beach", "stealth");
	
	public TabGUI() {
		super("tab gui", "tabguiiiii.", 0, Category.BEACHHOUSE);
		this.addSettings(theme);
	}
	
	@Override
	public void onEnable() {
		Main.EVENTBUS.subscribe(overlayListener);
		Main.EVENTBUS.subscribe(keyListener);
		expanded = true;
	}
	
	@Override
	public void onDisable() {
		Main.EVENTBUS.unsubscribe(overlayListener);
		Main.EVENTBUS.unsubscribe(keyListener);
	}
	
	int categoryIndex = 0;
	int moduleIndex = 0;
	boolean expanded;
	List<Module> currentModules = ModuleManager.getModulesByCategory(Category.values()[categoryIndex]);;
	
	TextRenderer tr = MinecraftClient.getInstance().textRenderer;

	@EventHandler
	private final Listener<EventDrawOverlay> overlayListener = new Listener<>(e -> {
		// categories
		int boxColor = 0x80E6AB17;
		if(theme.is("stealth")) boxColor = 0x80000000;
		int selectorColor = 0xffF730FB;
		if(theme.is("stealth")) selectorColor = 0xffffffff;

		InGameHud.fill(e.matrix, 1, 12, 66, 85, boxColor);
		InGameHud.fill(e.matrix, 2, (categoryIndex * 12) + 13, 65, (categoryIndex * 12) + 24, selectorColor);

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

		// modules
		if (expanded) {
			InGameHud.fill(e.matrix, 70, 12, 140, (currentModules.size() * 14) + 12, 0x90000000);
			InGameHud.fill(e.matrix, 70, (moduleIndex * 14) + 12, 140, (moduleIndex * 14) + 26, 0xFF00A9A9);
			int _yOffset = 14;
			for (Module m : currentModules) {
				TextFormatting color;

				if (m.isEnabled())
					color = TextFormatting.WHITE;
				else
					color = TextFormatting.GRAY;

				tr.drawWithShadow(e.matrix, color + m.getName(), 70 + 2, _yOffset + 1, 0xffffffff);
				_yOffset += 14;
			}
		}

	});

	@EventHandler
	private final Listener<EventKeyPress> keyListener = new Listener<>(e -> {
		if (e.getKey() == GLFW.GLFW_KEY_UP) {
			if (!expanded) {
				if (categoryIndex == 0)
					categoryIndex = Category.values().length - 1;
				else
					categoryIndex--;
			} else {
				if (moduleIndex == 0)
					moduleIndex = currentModules.size() - 1;
				else
					moduleIndex--;
			}
		}

		if (e.getKey() == GLFW.GLFW_KEY_DOWN) {
			if (!expanded) {
				if (categoryIndex == Category.values().length - 1)
					categoryIndex = 0;
				else
					categoryIndex++;
			} else {
				if (moduleIndex == currentModules.size() - 1)
					moduleIndex = 0;
				else
					moduleIndex++;
			}
		}

		if (e.getKey() == GLFW.GLFW_KEY_RIGHT) {
			if (!expanded) {
				expanded = true;
				moduleIndex = 0;
			} else {
				currentModules.get(moduleIndex).toggle();
			}
		}

		if (e.getKey() == GLFW.GLFW_KEY_LEFT) {
			expanded = false;
		}

		if (e.getKey() == GLFW.GLFW_KEY_ENTER) {
			if (expanded) {
				currentModules.get(moduleIndex).toggle();
			}
		}
	});

}
