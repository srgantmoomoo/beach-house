package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.backend.events.EventGuiKeyPress;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import org.lwjgl.glfw.GLFW;

public class CommandLine extends Module {
    public static CommandLine INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "art", "blur", "art", "dim", "none");

    public CommandLine() {
        super("command line", "commandline", "does command line stuffy stuff.", GLFW.GLFW_KEY_C, Category.BEACHHOUSE);
        this.addSettings(background);
        INSTANCE = this;
    }
    public boolean isInCommandLine = false;

    @Override
    public void onEnable() {
        minecraft.openScreen(new CommandLineScreen());

        isInCommandLine = true;
    }

    @Override
    public void onDisable() {
        isInCommandLine = false;
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventGuiKeyPress) {
            if(((EventGuiKeyPress) e).getKey() == GLFW.GLFW_KEY_ESCAPE)
                this.disable();
        }
    }

}