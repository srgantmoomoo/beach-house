package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.EventGuiKeyPress;
import me.srgantmoomoo.beachhouse.gui.hud.HudScreen;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import org.lwjgl.glfw.GLFW;

//TODO hud editor doesn't seem to enable when toggling with commands.
public class HudEditor extends Module {
    public static HudEditor INSTANCE;

    public ModeSetting background = new ModeSetting("background", this, "beach", "beach", "art", "blur", "dim", "none");

    public HudEditor() {
        super("hud editor", "hudeditor", "edit ur hud an stuff.", GLFW.GLFW_KEY_V, Category.BEACHHOUSE);
        this.addSettings(background);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new HudScreen());
        Main.INSTANCE.load.loadHud();
    }

    @Override
    public void onDisable() {
        Main.INSTANCE.save.saveHud();
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventGuiKeyPress) {
            if(((EventGuiKeyPress) e).getKey() == GLFW.GLFW_KEY_ESCAPE)
                this.disable();

            //TODO disabling with the same key u use to enable doesn't seem to work for some reason.
        }
    }

}
