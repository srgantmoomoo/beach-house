package me.srgantmoomoo.beachhouse.impl.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.impl.setting.Setting;
import me.srgantmoomoo.beachhouse.impl.setting.settings.KeybindSetting;
import net.minecraft.client.MinecraftClient;

public class Module {
	
	private MinecraftClient mc = MinecraftClient.getInstance();
	
	public String name, description;
	public KeybindSetting keyCode = new KeybindSetting(0);
	public Category category;
	public boolean toggled;
	public int index;
	public List<Setting> settings = new ArrayList<Setting>();
	
	public Module(String name, String description, int key, Category category) {
		super();
		this.name = name;
		this.description = description;
		keyCode.code = key;
		this.addSettings(keyCode);
		this.category = category;
		this.toggled = false;
	}
	
	public void addSettings(Setting... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
	}
	
	public enum Category {
		Player("player"), RENDER("render");
		
		public String name;
		public int moduleIndex;
		
		Category(String name) {
			this.name = name;
		}
	}
	
	public String getName() {
		return this.name;
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
		this.toggled = !this.toggled;
		if(this.toggled) this.onEnable();
		else this.onDisable();
		
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		if(this.toggled) {
			this.onEnable();
		}else {
			this.onDisable();
		}
		if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
	}
	
	public void onEnable() {
		Main.EVENTBUS.register(this);
	}
	
	public void onDisable() {
		Main.EVENTBUS.unregister(this);
	}

}
