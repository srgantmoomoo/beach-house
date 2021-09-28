package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {
    public static ClickGui INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "art", "blur", "art", "dim", "none");
    public BooleanSetting dynamicSide = new BooleanSetting("dynamicSide", this, true);
    public BooleanSetting hover = new BooleanSetting("hover", this, true);

    public ClickGui() {
        super("click gui", "clickgui", "does clicky click clack stuff.", GLFW.GLFW_KEY_B, Category.BEACHHOUSE);
        this.addSettings(background, hover, dynamicSide);
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
        if(e instanceof EventTick) {
            if(InputUtil.isKeyPressed(minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE))
                this.disable();
        }
    }

}
