package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;

public class WaterMark extends Module {
	public ModeSetting color = new ModeSetting("color", this, "beach", "beach", "white", "orange", "blue", "rainbow");
	public ModeSetting style = new ModeSetting("style", this, "bh", "bh", "beachhouse");
	
	public WaterMark() {
		super("water mark", "watermark", "is a watermark", 0, Category.BEACHHOUSE);
	}

}
