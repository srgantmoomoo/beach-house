package me.srgantmoomoo.bedroom.module.setting.settings;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;

/** the only value you need to worry about is the default value, it can either be true or false.
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class BooleanSetting extends Setting {
    public boolean enabled;

    public BooleanSetting(String name, Module parent, boolean enabled) {
        this.name = name;
        this.parent = parent;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (Main.save != null) {
            try {
                Main.save.saveSettings();
            } catch (Exception e) {}
        }
    }

    public void toggle() {
        setEnabled(!enabled);
    }
}
