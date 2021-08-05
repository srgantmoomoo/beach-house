package me.srgantmoomoo.bedroom.module;

import java.util.ArrayList;
import java.util.List;

import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.zero.alpine.listener.Listenable;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module.Category;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class ModuleManager implements Listenable {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
	}

	public static void onUpdate() {
		modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
	}

	public static void onEvent(Event e) {
		for(Module m :  Bedroom.moduleManager.getModules()){
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

}
