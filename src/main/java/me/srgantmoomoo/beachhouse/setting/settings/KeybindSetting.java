package me.srgantmoomoo.beachhouse.setting.settings;

import me.srgantmoomoo.beachhouse.module.Module;
import me.srgantmoomoo.beachhouse.setting.Setting;

public class KeybindSetting extends Setting {
	
	public int code;
	
	public KeybindSetting(int code) {
		this.name = "KeyBind";
		this.code = code;
	}
	
	public KeybindSetting(Module module) {
		// TODO Auto-generated constructor stub
	}

	public int getKeyCode() {
		return this.code;
	}
	
	public void setKeyCode(int code) {
		this.code = code;
	}

}