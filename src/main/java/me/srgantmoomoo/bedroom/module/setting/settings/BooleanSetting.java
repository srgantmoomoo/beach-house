package me.srgantmoomoo.bedroom.module.setting.settings;

import com.lukflug.panelstudio.setting.IBooleanSetting;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;

/** the only value you need to worry about is the default value, it can either be true or false.
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class BooleanSetting extends Setting implements IBooleanSetting {
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

        if (Bedroom.saveLoad != null) {
            Bedroom.saveLoad.save();
        }
    }

    public void switcher() {
        this.enabled = !this.enabled;

        if (Bedroom.saveLoad != null) {
            Bedroom.saveLoad.save();
        }
    }

    @Override
    public void toggle() {
        setEnabled(!isEnabled());
    }

    @Override
    public boolean isOn() {
        return isEnabled();
    }
}
