package me.srgantmoomoo.beachhouse.impl.setting.settings;

import me.srgantmoomoo.beachhouse.impl.module.Module;
import me.srgantmoomoo.beachhouse.impl.setting.Setting;

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