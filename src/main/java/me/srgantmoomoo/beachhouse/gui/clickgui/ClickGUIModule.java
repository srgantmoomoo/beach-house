package me.srgantmoomoo.beachhouse.gui.clickgui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;

public class ClickGUIModule extends Module {
    public static ClickGUIModule INSTANCE;

    public ColorSetting activeColor = new ColorSetting("that", this, new JColor(255, 255, 255, 255));
    public ColorSetting inactiveColor = new ColorSetting("that", this, new JColor(255, 255, 255, 255));
    public ColorSetting backgroundColor = new ColorSetting("that", this, new JColor(255, 255, 255, 255));
    public ColorSetting outlineColor = new ColorSetting("that", this, new JColor(255, 255, 255, 255));
    public ColorSetting fontColor = new ColorSetting("that", this, new JColor(255, 255, 255, 255));
    public NumberSetting opacity = new NumberSetting("opacity", this, 255, 0, 255, 5);
    public NumberSetting animationSpeed = new NumberSetting("aniSpeed", this, 10, 0, 100, 1);

    public ClickGUIModule() {
        super("click gui", "clickgui", "does clicky stuffyy", 0, Category.BEACHHOUSE);
        this.addSettings(activeColor, inactiveColor, backgroundColor, outlineColor, fontColor, opacity);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        Main.clickGUI.enterGUI();
    }
}
