package me.srgantmoomoo.bedroom.module.setting.settings;

import com.lukflug.panelstudio.setting.IKeybindSetting;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

public class KeybindSetting extends Setting implements IKeybindSetting {

    public int code;

    public KeybindSetting(int code) {
        this.name = "KeyBind";
        this.code = code;
    }

    public KeybindSetting(Module module) {
        // TODO Auto-generated constructor stub
    }

    public int getKeyCode() {
        return this.code;
    }

    public void setKeyCode(int code) {
        this.code = code;
    }

    @Override
    public int getKey() {
        return getKeyCode();
    }

    @Override
    public void setKey (int key) {
        setKeyCode(key);
    }

    @Override
    public String getKeyName() {
        String translationKey= InputUtil.Type.KEYSYM.createFromCode(getKey()).getTranslationKey();
        String translation=new TranslatableText(translationKey).getString();
        if (!translation.equals(translationKey)) return translation;
        return InputUtil.Type.KEYSYM.createFromCode(getKey()).getLocalizedText().getString();
    }

}