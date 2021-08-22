package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {
    public static ClickGui INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "blur", "blur", "dim", "none");

    public ClickGui() {
        super("click gui", "clickgui", "does clicky click clack stuff", GLFW.GLFW_KEY_RIGHT_CONTROL, Category.BEACHHOUSE);
        this.addSettings(background);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new ClickGuiScreen());
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(InputUtil.isKeyPressed(minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE))
                this.disable();
        }
    }

}