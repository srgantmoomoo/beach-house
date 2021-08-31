package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;

public class Watermark extends Module {
    public static Watermark INSTANCE;
    public BooleanSetting background = new BooleanSetting("background", this, false);
    public ModeSetting style = new ModeSetting("style", this, "style1", "style1", "style2", "style3");

    public Watermark() {
        super("watermark", "watermark", "waters the mark.", 0, Category.BEACHHOUSE);
        this.addSettings(style);
        INSTANCE = this;
    }

}
