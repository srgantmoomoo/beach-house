package me.srgantmoomoo.beachhouse.module.modules.combat;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;

public class Surround extends Module {
    public ModeSetting idkbutsomething = new ModeSetting("idkbutsomething", this, "forceMiddle", "forceMiddle", "dynamic");

    public Surround() {
        super("surround", "surround", "surrounds u wweeweri", 0, Category.COMBAT);
    }

}
