package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.EventGuiKeyPress;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {
    public static ClickGui INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "beach", "beach", "blur", "art", "dim", "none");
    public BooleanSetting interactWithWall = new BooleanSetting("interactWithWall", this, true);
    public BooleanSetting hover = new BooleanSetting("hover", this, true);

    public ClickGui() {
        super("click gui", "clickgui", "does clicky click clack stuff.", GLFW.GLFW_KEY_B, Category.BEACHHOUSE);
        this.addSettings(background, hover, interactWithWall);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new ClickGuiScreen());
        Main.load.loadGui();
    }

    @Override
    public void onDisable() {
        Main.save.saveGui();
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventGuiKeyPress) {
            if(((EventGuiKeyPress) e).getKey() == GLFW.GLFW_KEY_ESCAPE)
                this.disable();
        }
    }

}
