package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.glfw.GLFW;
// TODO tab gets all jumpy when disabled than enabled.
public class TabGUI extends Module {
	public ModeSetting theme = new ModeSetting("theme", this, "beach", "beach", "stealth");
	public BooleanSetting arrow = new BooleanSetting("arrow", this, true);
	
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

		int backgroundColor = 0x80000000;
		int tabColor = 0xff000000;
		if(theme.is("beach")) backgroundColor = 0x80E6AB17;
		if(theme.is("beach")) tabColor = 0xffF730FB;

		InGameHud.fill(e.matrix, 2, 12, 60, 86, backgroundColor);
		InGameHud.fill(e.matrix, 3, 13 + currentTab * 12, 59, 14 + currentTab * 12 + 11, tabColor);
		tr.drawWithShadow(e.matrix, ">", currentTab == 3 ? 54 : 52, 15 + currentTab * 12, 0xffffffff);

		int count = 0;
		for (Category c : Module.Category.values()) {

			String catName = c.name;
			if(c.name.equals("miscellaneous")) catName = "misc";
			if(c.name.equals("beach house")) catName = "beach";

			int catLength = 1;
			if(c.name.equals("player")) catLength = 15;
			if(c.name.equals("render")) catLength = 14;
			if(c.name.equals("combat")) catLength = 14;
			if(c.name.equals("movement")) catLength = 8;
			if(c.name.equals("miscellaneous")) catLength = 21;
			if(c.name.equals("beach house")) catLength = 16;

			tr.drawWithShadow(e.matrix, catName, catLength, 15 + count * 12, 0xffffffff);
			count++;
		}

		if (expanded) {
			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Main.moduleManager.getModulesByCategory(category);

			if (modules.size() == 0)
				return;

			InGameHud.fill(e.matrix, 61, 12, 130, 14 + modules.size() * 12, backgroundColor);
			InGameHud.fill(e.matrix, 62, 14 + category.moduleIndex * 12 - 1, 129, 14 + category.moduleIndex * 12 + 11, tabColor);

			count = 0;
			for (Module m : modules) {
				tr.drawWithShadow(e.matrix, m.name, 64, 15 + count * 12, -1);
				if(m.isEnabled()) {
					InGameHud.fill(e.matrix, 127, 14 + count * 12, 128, 23 + count * 12, 0xffffffff);
				}
				count++;
			}
		}
	});

	@EventHandler
	private final Listener<EventKeyPress> keyListener = new Listener<>(e -> {

		int code = ((EventKeyPress)e).getKey();

		Category category = Module.Category.values()[currentTab];
		List<Module> modules = Main.moduleManager.getModulesByCategory(category);

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
