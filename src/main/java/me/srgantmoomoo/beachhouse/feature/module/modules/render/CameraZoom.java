package me.srgantmoomoo.beachhouse.feature.module.modules.render;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;

public class CameraZoom extends Module {
    public NumberSetting scale = new NumberSetting("scale", this, 2, 1, 10, 1);

    public CameraZoom() {
        super("zoom", "zoom", "zooms ur camera.", 0, Category.RENDER);
        this.addSettings(scale);
    }
    public double originalFov;
    public double originalSens;

    @Override
    public void onEnable() {
        originalFov = minecraft.options.fov;
        originalSens = minecraft.options.mouseSensitivity;

        minecraft.options.fov = originalFov / scale.getValue();
        minecraft.options.mouseSensitivity = originalSens / scale.getValue();
    }

    @Override
    public void onDisable() {
        minecraft.options.fov = originalFov;
        minecraft.options.mouseSensitivity = originalSens;
    }

}
