
package me.srgantmoomoo.bedroom.module.setting.settings;

import com.lukflug.panelstudio.setting.IColorSetting;
import com.lukflug.panelstudio.theme.ITheme;
import me.srgantmoomoo.bedroom.api.util.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting implements IColorSetting {

    private boolean rainbow;
    private JColor value;

    public ColorSetting (String name, Module parent, final JColor value) {
        this.name = name;
        this.parent = parent;
        this.value = value;
    }

    public JColor getColorValue() {
        if (rainbow) {
            return getRainbow(0, this.getColor().getAlpha());
        }
        return this.value;
    }

    public static JColor getRainbow(int incr, int alpha) {
        JColor color =  JColor.fromHSB(((System.currentTimeMillis() + incr * 200)%(360*20))/(360f * 20),0.5f,1f);
        return new JColor(color.getRed(), color.getBlue(), color.getGreen(), alpha);
    }


    public void setValue (boolean rainbow, final JColor value) {
        this.rainbow = rainbow;
        this.value = value;
    }

    public long toInteger() {
        return this.value.getRGB() & (0xFFFFFFFF);
    }

    public void fromInteger (long number) {
        this.value = new JColor(Math.toIntExact(number & 0xFFFFFFFF),true);
    }

    public JColor getColor1() {
        return this.value;
    }

    @Override
    public Color getValue() {
        if (rainbow) {
            int speed=10;
            return ITheme.combineColors(Color.getHSBColor((System.currentTimeMillis()%(360*speed))/(float)(360*speed),1,1),getColorValue());
        }
        else return getColorValue();
    }

    @Override
    public void setValue(Color value) {

    }

    @Override
    public Color getColor() {
        return getColorValue();
    }

    @Override
    public boolean getRainbow() {
        return rainbow;
    }

    @Override
    public void setRainbow (boolean rainbow) {
        this.rainbow=rainbow;
    }

    @Override
    public boolean hasAlpha() {
        return true;
    }

    @Override
    public boolean allowsRainbow() {
        return true;
    }

    @Override
    public boolean hasHSBModel() {
        return true;
    }
}