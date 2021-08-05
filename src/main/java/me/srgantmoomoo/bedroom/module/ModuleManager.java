package me.srgantmoomoo.bedroom.module;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module.Category;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class ModuleManager {

	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
	}

	public static void onEvent(Event e) {
		for(Module m : Bedroom.moduleManager.getModules()){
			if(!m.isEnabled())
				continue;

			m.onEvent(e);;
		}
	}

	public boolean isModuleEnabled(String name) {
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m.isEnabled();
	}

	public Module getModule(String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public Module getModuleByID(String moduleID) {
		for(Module m : ModuleManager.modules) {
			if(m.getID().equalsIgnoreCase(moduleID)) {
				return m;
			}
		}
		return null;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();

		for(Module m : ModuleManager.modules) {
				if(m.getCategory() == c)
					modules.add(m);
		} return modules;
	}

	// for key binds (called in MixinKeyboard).
	public void keyPress(int key, int scancode) {
		EventKeyPress e = new EventKeyPress(key, scancode);
		if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3))
			return;

		modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(Module::toggle);
	}

}
