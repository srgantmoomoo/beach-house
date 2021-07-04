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
	}
	
	@Override
	public void onDisable() {
		Main.EVENTBUS.unsubscribe(overlayListener);
		Main.EVENTBUS.unsubscribe(keyListener);
	}

	public int currentTab;
	public boolean expanded;
	public boolean Tab;

	@EventHandler
	private final Listener<EventDrawOverlay> overlayListener = new Listener<>(e -> {
		TextRenderer tr = MinecraftClient.getInstance().textRenderer;

		InGameHud.fill(e.matrix, 2, 12, 53, 71, 0x80000000);
		InGameHud.fill(e.matrix, 3, 14 + currentTab * 15 - 1, 52, 14 + currentTab * 15 + 11, 0xff000000); //0x9993d3d3

		int count = 0;
		for (Category c : Module.Category.values()) {
			tr.drawWithShadow(e.matrix, c.name, 4, 4 + count * 14, 0xffffffff);
			count++;
		}

		if (expanded) {
			Category category = Module.Category.values()[currentTab];
			List<Module> modules = ModuleManager.getModulesByCategory(category);

			if (modules.size() == 0)
				return;

			InGameHud.fill(e.matrix, 55, 12, 130, 11 + modules.size() * 15, 0x80000000);
			InGameHud.fill(e.matrix, 56, 14 + category.moduleIndex * 15 - 1, 129, 14 + category.moduleIndex * 15 + 11, 0xff000000);

			count = 0;
			for (Module m : modules) {
				tr.drawWithShadow(e.matrix, m.name, 4 + 53, 18 + count * 14, -1);
				count++;
			}
		}
	});

	@EventHandler
	private final Listener<EventKeyPress> keyListener = new Listener<>(e -> {

		int code = ((EventKeyPress)e).getKey();

		Category category = Module.Category.values()[currentTab];
		List<Module> modules = ModuleManager.getModulesByCategory(category);

		if(code == GLFW.GLFW_KEY_UP) {
			if(expanded) {
				if(category.moduleIndex <= 0) {
					category.moduleIndex = modules.size() - 1;
				}else
					category.moduleIndex--;
			}else {
				if(currentTab <= 0) {
					currentTab = Module.Category.values().length - 1;
				}else
					currentTab--;
			}
		}

		if(code == GLFW.GLFW_KEY_DOWN) {
			if (expanded) {
				if(category.moduleIndex >= modules.size() - 1) {
					category.moduleIndex = 0;
				}else
					category.moduleIndex++;
			}else {
				if(currentTab >= Module.Category.values().length - 1) {
					currentTab = 0;
				}else
					currentTab++;
			}
		}

		if(code == GLFW.GLFW_KEY_RIGHT) {
			if(expanded && modules.size() !=0) {
				Module module = modules.get(category.moduleIndex);
				if(!module.name.equals("TabGUI"))
					module.toggle();
			}else {
				expanded = true;


			}
		}

		if(code == GLFW.GLFW_KEY_LEFT) {
			expanded = false;
		}
	});

}
