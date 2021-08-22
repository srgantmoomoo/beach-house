
package me.srgantmoomoo.bedroom.module.setting;

import com.lukflug.panelstudio.base.IBoolean;
import com.lukflug.panelstudio.setting.ILabeled;
import com.lukflug.panelstudio.setting.ISetting;
import me.srgantmoomoo.bedroom.module.Module;

import java.util.stream.Stream;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public abstract class Setting implements ILabeled {

    public String name;
    public Module parent;
    public boolean focused;

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public IBoolean isVisible() {
        return ()->true;
    }

    public Stream<ISetting<?>> getSubSettings() {
        return null;
    }

}