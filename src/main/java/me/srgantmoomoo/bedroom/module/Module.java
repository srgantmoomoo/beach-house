package me.srgantmoomoo.bedroom.module;

import java.util.*;
import java.util.stream.Stream;

import com.lukflug.panelstudio.base.IBoolean;
import com.lukflug.panelstudio.base.IToggleable;
import com.lukflug.panelstudio.setting.ICategory;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IModule;
import com.lukflug.panelstudio.setting.ISetting;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.KeybindSetting;
import net.minecraft.client.MinecraftClient;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public abstract class Module implements IModule {

    public static final MinecraftClient minecraft = MinecraftClient.getInstance();

    public String name, ID, description;
    public KeybindSetting keyCode = new KeybindSetting(0);
    public Category category;
    public boolean enabled;
    public int index;
    public List<Setting> settings = new ArrayList<Setting>();

    public Module(String name, String ID, String description, int key, Category category) {
        super();
        this.name = name;
        this.ID = ID;
        this.description = description;
        keyCode.code = key;
        addSettings(keyCode);
        this.category = category;
        enabled = false;
    }

    //TODO make categories customizable.... and maybe switch the whole system to annotations to make life easier.
    public enum Category implements ICategory {
        PLAYER("player"), RENDER("render"), COMBAT("combat"), MOVEMENT("movement"), MISCELLANEOUS("miscellaneous"), BEACHHOUSE("beach house");
        public String name;
        public int moduleIndex;
        public static Random random=new Random();

        Category(String name) {
            this.name = name;
        }

        @Override
        public String getDisplayName() {
            return name;
        }

        @Override
        public Stream<IModule> getModules() {
            return Bedroom.moduleManager.modules.stream().map(module->module);
        }

        public static IClient getClient() {
            return new IClient() {
                @Override
                public Stream<ICategory> getCategories() {
                    return Arrays.stream(Category.values());
                }
            };
        }

    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(Arrays.asList(settings));
        this.settings.sort(Comparator.comparingInt(s -> s == keyCode ? 1 : 0));
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.ID;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getDesc() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return keyCode.code;
    }

    public void setKey(int key) {
        this.keyCode.code = key;

        if(Bedroom.saveLoad != null) {
            Bedroom.saveLoad.save();
        }
    }

    public void toggle() {
        enabled = !enabled;
        if(enabled) {
            enable();
        }else {
            disable();
        }

        if(Bedroom.saveLoad != null) {
            Bedroom.saveLoad.save();
        }
    }

    public boolean isActive() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if(Bedroom.saveLoad != null) {
            Bedroom.saveLoad.save();
        }
    }

    public void enable() {
        onEnable();
        setEnabled(true);
    }

    public void disable() {
        onDisable();
        setEnabled(false);
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    @SuppressWarnings("rawtypes")
    public void onEvent(Event e) {

    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public IBoolean isVisible() {
        return ()->true;
    }

    @Override
    public IToggleable isEnabled() {
        return new IToggleable() {
            @Override
            public boolean isOn() {
                return enabled;
            }

            @Override
            public void toggle() {
                enabled=!enabled;
            }
        };
    }

    @Override
    public Stream<ISetting<?>> getSettings() {
        return settings.stream().filter(setting->setting instanceof ISetting).sorted((a,b)->a.name.compareTo(b.name)).map(setting->(ISetting<?>)setting);
    }

}