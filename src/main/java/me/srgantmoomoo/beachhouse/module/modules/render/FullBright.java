package me.srgantmoomoo.beachhouse.module.modules.render;


import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;

public class FullBright extends Module {
    public ModeSetting mode = new ModeSetting("mode", this, "table", "table", "gamma", "nightVision");

    public FullBright() {
        super("full bright", "fullbright", "fullness of brightness.", 0, Category.RENDER);
    }

    private double gamma = 9;
    public static double originalGamma = 1;
    public static boolean goingDown = false;

    @Override
    public void onEnable() {
        if (goingDown) {
            minecraft.options.gamma = originalGamma;
            goingDown = false;
        }
        originalGamma = minecraft.options.gamma;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            minecraft.options.gamma += (gamma - minecraft.options.gamma) * 0.1f;
        }
    }

    // fullbright is really disabled in MixinClientWorld
    @Override
    public void onDisable() {
        goingDown = true;
    }

}
