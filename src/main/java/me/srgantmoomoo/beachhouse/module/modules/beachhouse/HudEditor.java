package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.gui.hud.HudScreen;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import me.srgantmoomoo.bedroom.api.util.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

//TODO hud editor doesn't seem to enable when toggling with commands.
public class HudEditor extends Module {
    public static HudEditor INSTANCE;

    public ColorSetting solidColor = new ColorSetting("color", this, new JColor(172, 172, 172, 255));
    public ModeSetting style = new ModeSetting("style", this, "beach", "dull", "vibrant", "beach", "solid", "rainbow");
    public BooleanSetting background = new BooleanSetting("background", this, false);
    public BooleanSetting forgeHax = new BooleanSetting("forgeHax", this, false);

    public HudEditor() {
        super("hud editor", "hudeditor", "edit ur hud an stuff", GLFW.GLFW_KEY_RIGHT_SHIFT, Category.BEACHHOUSE);
        this.addSettings(solidColor, style, background, forgeHax);
        INSTANCE = this;
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
