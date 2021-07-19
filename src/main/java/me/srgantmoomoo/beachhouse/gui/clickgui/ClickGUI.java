package me.srgantmoomoo.beachhouse.gui.clickgui;

import com.lukflug.panelstudio.CollapsibleContainer;
import com.lukflug.panelstudio.DraggableContainer;
import com.lukflug.panelstudio.SettingsAnimation;
import com.lukflug.panelstudio.mc16.MinecraftGUI;
import com.lukflug.panelstudio.settings.BooleanComponent;
import com.lukflug.panelstudio.settings.NumberComponent;
import com.lukflug.panelstudio.settings.SimpleToggleable;
import com.lukflug.panelstudio.settings.Toggleable;
import com.lukflug.panelstudio.theme.ClearTheme;
import com.lukflug.panelstudio.theme.SettingsColorScheme;
import com.lukflug.panelstudio.theme.Theme;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.ColorMain;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;


public class ClickGUI extends MinecraftGUI {
    public static final int WIDTH = 100, HEIGHT = 12, DISTANCE = 10;
    private final Toggleable colorToggle;
    private final GUIInterface guiInterface;
    private final Theme theme;
    private final com.lukflug.panelstudio.ClickGUI gui;

    MinecraftClient minecraft = MinecraftClient.getInstance();
    TextRenderer textRenderer = minecraft.textRenderer;

    public ClickGUI() {
        colorToggle = new Toggleable() {
            @Override
            public void toggle() {
                ColorMain.colorModel.increment();
            }

            @Override
            public boolean isOn() {
                return ColorMain.colorModel.is("RGB");
            }
        };

        guiInterface = new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return "coolheck:gui/";
            }

            MatrixStack matrix;
            @Override
            public void drawString(Point pos, String s, Color c) {
                end();
                textRenderer.drawWithShadow(matrix, s, pos.x, pos.y, 0xffffffff);
                begin();
            }

            @Override
            public int getFontWidth(String s) {
                return textRenderer.getWidth(s);
            }

            @Override
            public int getFontHeight() {
                return textRenderer.fontHeight;
            }
        };

        theme = new ClearTheme(new SettingsColorScheme(ClickGUIModule.INSTANCE.activeColor, ClickGUIModule.INSTANCE.inactiveColor, ClickGUIModule.INSTANCE.backgroundColor,
                ClickGUIModule.INSTANCE.outlineColor, ClickGUIModule.INSTANCE.fontColor, ClickGUIModule.INSTANCE.opacity), false, HEIGHT, 2);
        gui = new com.lukflug.panelstudio.ClickGUI(guiInterface, null);

        Point pos = new Point(DISTANCE, DISTANCE);
        for(Module.Category category : Module.Category.values()) {
            DraggableContainer panel = new DraggableContainer(category.name, null, theme.getPanelRenderer(), new SimpleToggleable(false),
                    new SettingsAnimation(ClickGUIModule.INSTANCE.animationSpeed), null, new Point(pos), WIDTH);
            gui.addComponent(panel);

            for(Module module : Bedroom.moduleManager.getModulesByCategory(category)) {
                CollapsibleContainer container = new CollapsibleContainer(module.name, null, theme.getContainerRenderer(), new SimpleToggleable(false), new SettingsAnimation(ClickGUIModule.INSTANCE.animationSpeed), module);
                panel.addComponent(container);

                for(Setting setting : module.settings) {
                    if(setting instanceof BooleanSetting)
                        container.addComponent(new BooleanComponent(setting.name, null, theme.getComponentRenderer(), (BooleanSetting)setting));

                    if(setting instanceof NumberSetting) {
                        container.addComponent(new NumberComponent(setting.name, null, theme.getComponentRenderer(), (NumberSetting)setting,
                                ((NumberSetting)setting).getMinimum(), ((NumberSetting)setting).getMaximum()));

                   //if(setting instanceof ModeSetting) {

                    //}
                    }
                }
            }
        }
    }

    @Override
    protected com.lukflug.panelstudio.ClickGUI getGUI() {
        return gui;
    }

    @Override
    protected GUIInterface getInterface() {
        return guiInterface;
    }

    @Override
    protected int getScrollSpeed() {
        return 10;
    }
}
