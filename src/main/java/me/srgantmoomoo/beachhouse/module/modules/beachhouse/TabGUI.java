package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import java.util.List;

import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.glfw.GLFW;

public class TabGUI extends Module {
	public BooleanSetting tab = new BooleanSetting("tab", this, false);
	public BooleanSetting miniTab = new BooleanSetting("miniTab", this, true);
	public BooleanSetting arrow = new BooleanSetting("arrow", this, false);
	
	public TabGUI() {
		super("tab gui", "tabgui", "tabguiiiii.", 0, Category.BEACHHOUSE);
		this.addSettings(tab, miniTab, arrow);
	}

	public int currentTab;
	public boolean expanded;
	public boolean Tab;

	@Override
	public void onEvent(Event e) {
		if(e instanceof DrawOverlayEvent) {
			TextRenderer tr = minecraft.textRenderer;

			int backgroundColor = 0x90000000;
			int tabColor = 0xff000000;
			int primaryColor = 0xffEB78DF;

			InGameHud.fill(((DrawOverlayEvent) e).matrix, 2, 12, 60, 86, backgroundColor);
			if(tab.isEnabled()) InGameHud.fill(((DrawOverlayEvent) e).matrix, 3, 13 + currentTab * 12, 59, 14 + currentTab * 12 + 11, tabColor);
			if(miniTab.isEnabled()) InGameHud.fill(((DrawOverlayEvent) e).matrix, 3, 13 + currentTab * 12, 4, 14 + currentTab * 12 + 11, primaryColor);

			if(arrow.isEnabled()) tr.drawWithShadow(((DrawOverlayEvent) e).matrix, ">", currentTab == 3 ? 54 : 52, 15 + currentTab * 12, 0xffffffff);

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

				tr.drawWithShadow(((DrawOverlayEvent) e).matrix, catName, catLength, 15 + count * 12, 0xffffffff);
				count++;
			}

			if (expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Bedroom.moduleManager.getModulesByCategory(category);

				if (modules.size() == 0)
					return;

				InGameHud.fill(((DrawOverlayEvent) e).matrix, 61, 12, 130, 14 + modules.size() * 12, backgroundColor);
				if(tab.isEnabled()) InGameHud.fill(((DrawOverlayEvent) e).matrix, 62, 14 + category.moduleIndex * 12 - 1, 129, 14 + category.moduleIndex * 12 + 11, tabColor);
				if(miniTab.isEnabled()) tr.draw(((DrawOverlayEvent) e).matrix, "-", 131, 14 + category.moduleIndex * 12 + 1, primaryColor);

				count = 0;
				for (Module m : modules) {
					tr.drawWithShadow(((DrawOverlayEvent) e).matrix, m.name, 64, 15 + count * 12, -1);
					if(m.isEnabled()) {
						InGameHud.fill(((DrawOverlayEvent) e).matrix, 127, 14 + count * 12, 128, 23 + count * 12, 0xffffffff);
					}
					count++;
				}
			}
		}

		if(e instanceof EventKeyPress) {
			int code = ((EventKeyPress)e).getKey();

			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Bedroom.moduleManager.getModulesByCategory(category);

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
		}
	}

}
