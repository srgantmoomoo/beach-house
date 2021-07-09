package me.srgantmoomoo.bedroom.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.KeybindSetting;
import me.zero.alpine.listener.Listenable;
import net.minecraft.client.MinecraftClient;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class Module implements Listenable {
	
	protected static final MinecraftClient mc = MinecraftClient.getInstance();
	public static ArrayList<Module> modules;
	
	public String name, ID, description;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	public boolean enabled;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, String ID, String description, int key, Category category) {
		super();
		this.name = name;
		this.ID = ID;
		this.description = description;
		keyCode.code = key;
		addSettings(keyCode);
		this.category = category;
		enabled = false;
	}
	
	public enum Category {
		PLAYER("player"), RENDER("render"), COMBAT("combat"), MOVEMENT("movement"), MISCELLANEOUS("miscellaneous"), BEACHHOUSE("beach house");
		public String name;
		public int moduleIndex;
		
		Category(String name) {
			this.name = name;
		}
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	}
	
	public String getName() {
		return this.name;
	}

	public String getID() {
		return this.ID;
	}

	public Category getCategory() {
		return this.category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getKey() {
		return keyCode.code;
	}
	
	public void setKey(int key) {
		this.keyCode.code = key;
		
		 if(Main.saveLoad != null) {
				Main.saveLoad.save();
		 }
	} 
	
	public void toggle() {
		enabled = !enabled;
		if(enabled) {
			enable();
		}else {
			disable();
		}
		
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled) {
			Main.EVENTBUS.subscribe(this);
		}else {
			Main.EVENTBUS.unsubscribe(this);
		}
		
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public void enable() {
		onEnable();
		setEnabled(true);
	}

	public void disable() {
		onDisable();
		setEnabled(false);
	}
	
	public void onEnable() { }
	
	public void onDisable() { }
	
	public void onUpdate() { }

}
