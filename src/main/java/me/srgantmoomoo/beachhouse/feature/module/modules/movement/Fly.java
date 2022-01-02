package me.srgantmoomoo.beachhouse.feature.module.modules.movement;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;

public class Fly extends Module {
    public NumberSetting speed = new NumberSetting("speed", this, 100, 1, 1000, 1);

    public Fly() {
        super("fly", "fly", "fly and sutff.", 0, Category.MOVEMENT);
        this.addSettings(speed);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(minecraft.player == null || minecraft.world == null)
                return;

            minecraft.player.getAbilities().flying = true;
            minecraft.player.getAbilities().setFlySpeed((float) (speed.getValue() / 5000));
            minecraft.player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onDisable() {
        if(minecraft.player == null || minecraft.world == null)
            return;

        minecraft.player.getAbilities().flying = false;
        minecraft.player.sendAbilitiesUpdate();
    }

}

