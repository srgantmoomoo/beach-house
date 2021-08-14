package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.gui.hud.HudScreen;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

//TODO hud editor doesn't seem to enable when toggling with commands.
public class HudEditor extends Module {

    public HudEditor() {
        super("hud editor", "hudeditor", "edit ur hud an stuff", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.BEACHHOUSE);
    }

    @Override
    public void onEnable() {
        minecraft.openScreen(new HudScreen());
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(InputUtil.isKeyPressed(minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE))
                this.disable();

            //TODO disabling with the same key u use to enable doesn't seem to work for some reason.
            if(InputUtil.isKeyPressed(minecraft.getWindow().getHandle(), this.getKey()))
                this.disable();
        }
    }

}
