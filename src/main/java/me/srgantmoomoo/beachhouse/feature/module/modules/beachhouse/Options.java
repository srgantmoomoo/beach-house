package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.backend.events.EventGuiKeyPress;
import me.srgantmoomoo.beachhouse.gui.options.OptionsScreen;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import org.lwjgl.glfw.GLFW;

public class Options extends Module {
    public static Options INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "beach", "beach", "blur", "art", "dim", "none");

    public Options() {
        super("options", "options", "do options stuff with client n stuff.", GLFW.GLFW_KEY_N, Category.BEACHHOUSE);
        this.addSettings(background);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new OptionsScreen());
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventGuiKeyPress) {
            if(((EventGuiKeyPress) e).getKey() == GLFW.GLFW_KEY_ESCAPE)
                this.disable();
        }
    }

}