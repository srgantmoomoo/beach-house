package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CommandLine extends Module {
    public static CommandLine INSTANCE;

    //public ModeSetting background = new ModeSetting("background", this, "art", "blur", "art", "dim", "none");
    //public BooleanSetting dynamicSide = new BooleanSetting("dynamicSide", this, true);
    //public BooleanSetting hover = new BooleanSetting("hover", this, true);

    public CommandLine() {
        super("command line", "commandline", "does command line stuffy stuff.", GLFW.GLFW_KEY_C, Category.BEACHHOUSE);
        //this.addSettings(background, hover, dynamicSide);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new CommandLineScreen());
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(InputUtil.isKeyPressed(minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE))
                this.disable();
        }
    }

}