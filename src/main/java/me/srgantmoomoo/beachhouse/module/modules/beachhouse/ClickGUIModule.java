package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

public class ClickGUIModule extends Module {
    public static ClickGUIModule INSTANCE;

    public ModeSetting colorModel = new ModeSetting("colorModel", this, "rgb", "rgb", "hsb");

    public NumberSetting rainbowSpeed = new NumberSetting("rainbowSpeed", this, 32, 1, 100, 1);
    public NumberSetting scrollSpeed = new NumberSetting("ColorModel", this, 10, 0, 20, 1);
    public NumberSetting animationSpeed = new NumberSetting("animationSpeed", this, 200, 0, 1000, 1);
    public ModeSetting theme = new ModeSetting("theme", this, "gamesense", "gamesense", "smthnelse");
    public ModeSetting layout = new ModeSetting("layout", this, "classicPanel", "classicPanel", "searchableCSGO_");

    public ClickGUIModule() {
        super("clickguim", "clickguim", "Module containing ClickGUI settings.", 0, Category.BEACHHOUSE);
        this.addSettings(colorModel, rainbowSpeed, scrollSpeed, animationSpeed, theme, layout);

        INSTANCE = this;
    }

    public enum ColorModel {
        RGB, HSB;
    }

    public enum Theme {
        Clear, GameSense, Rainbow, Windows31, Impact;
    }

    public enum Layout {
        ClassicPanel, PopupPanel, DraggablePanel, SinglePanel, PanelMenu, ColorPanel, CSGOHorizontal, CSGOVertical, CSGOCategory, SearchableCSGO;
    }

    @Override
    public void onEnable() {
        Main.gui.enterGUI();
    }
}
