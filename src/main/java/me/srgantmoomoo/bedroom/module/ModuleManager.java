package me.srgantmoomoo.bedroom.module;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.modules.beachhouse.*;
import me.srgantmoomoo.beachhouse.modules.combat.*;
import me.srgantmoomoo.beachhouse.modules.miscellaneous.*;
import me.srgantmoomoo.beachhouse.modules.movement.*;
import me.srgantmoomoo.beachhouse.modules.player.*;
import me.srgantmoomoo.beachhouse.modules.render.*;
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

public class ModuleManager {
	
	public static ArrayList<Module> modules;
	
	public ModuleManager() {
		Main.EVENTBUS.subscribe(listener);
		
		modules = new ArrayList<>();
		// combat
		modules.add(new SwingAura());
		modules.add(new Criticals());
		modules.add(new AutoCrystal());
		
		// player
		modules.add(new Jesus());
		
		// misc
		modules.add(new AntiNick());
		
		// movement
		modules.add(new Sprint());
		modules.add(new Speed());
		modules.add(new Strafe());
		
		// render
		modules.add(new FullBright());
		modules.add(new Xray());
		
		// beachhouse
		modules.add(new ModuleList());
		modules.add(new TabGUI());
		modules.add(new ClickGUI());
	}
	
	public static void onUpdate() {
		modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
	}
	
	public static boolean isModuleEnabled(String name) {
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m.isEnabled();
	}
	
	public Module getModule (String name) {
		for (Module m : ModuleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public static ArrayList<Module> getModules() {
		return modules;
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : ModuleManager.modules) {
			if(!m.getName().equals("Esp2dHelper")) {
			if(m.getCategory() == c)
				modules.add(m);
		}
		}
		return modules;
	}
	
	public static Module getModuleByName(String name) {
		Module m = modules.stream().filter(mm->mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
		return m;
	}
	
	@EventHandler
	private final Listener<EventKeyPress> listener = new Listener<>(e -> {
		if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_F3))
			return;

		modules.stream().filter(m -> m.getKey() == e.getKey()).forEach(Module::toggle);
	});
	
}
