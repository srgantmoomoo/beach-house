package me.srgantmoomoo.beachhouse.gui.newclickgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import me.srgantmoomoo.beachhouse.module.modules.beachhouse.ClickGUIModule;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import org.lwjgl.glfw.GLFW;

import com.lukflug.panelstudio.base.Animation;
import com.lukflug.panelstudio.base.Context;
import com.lukflug.panelstudio.base.IBoolean;
import com.lukflug.panelstudio.base.IInterface;
import com.lukflug.panelstudio.base.IToggleable;
import com.lukflug.panelstudio.base.SettingsAnimation;
import com.lukflug.panelstudio.base.SimpleToggleable;
import com.lukflug.panelstudio.component.IComponent;
import com.lukflug.panelstudio.component.IFixedComponent;
import com.lukflug.panelstudio.component.IResizable;
import com.lukflug.panelstudio.component.IScrollSize;
import com.lukflug.panelstudio.container.IContainer;
import com.lukflug.panelstudio.hud.HUDGUI;
import com.lukflug.panelstudio.layout.CSGOLayout;
import com.lukflug.panelstudio.layout.ChildUtil.ChildMode;
import com.lukflug.panelstudio.layout.ComponentGenerator;
import com.lukflug.panelstudio.layout.IComponentAdder;
import com.lukflug.panelstudio.layout.IComponentGenerator;
import com.lukflug.panelstudio.layout.ILayout;
import com.lukflug.panelstudio.layout.PanelAdder;
import com.lukflug.panelstudio.layout.PanelLayout;
import com.lukflug.panelstudio.layout.SearchableLayout;
import com.lukflug.panelstudio.layout.SinglePanelAdder;
import com.lukflug.panelstudio.layout.StackedPanelAdder;
import com.lukflug.panelstudio.mc17.MinecraftHUDGUI;
import com.lukflug.panelstudio.popup.CenteredPositioner;
import com.lukflug.panelstudio.popup.IPopupPositioner;
import com.lukflug.panelstudio.popup.MousePositioner;
import com.lukflug.panelstudio.popup.PanelPositioner;
import com.lukflug.panelstudio.popup.PopupTuple;
import com.lukflug.panelstudio.setting.IBooleanSetting;
import com.lukflug.panelstudio.setting.IClient;
import com.lukflug.panelstudio.setting.IColorSetting;
import com.lukflug.panelstudio.setting.IEnumSetting;
import com.lukflug.panelstudio.setting.INumberSetting;
import com.lukflug.panelstudio.setting.Labeled;
import com.lukflug.panelstudio.theme.ClearTheme;
import com.lukflug.panelstudio.theme.GameSenseTheme;
import com.lukflug.panelstudio.theme.IColorScheme;
import com.lukflug.panelstudio.theme.ITheme;
import com.lukflug.panelstudio.theme.IThemeMultiplexer;
import com.lukflug.panelstudio.theme.ImpactTheme;
import com.lukflug.panelstudio.theme.OptimizedTheme;
import com.lukflug.panelstudio.theme.RainbowTheme;
import com.lukflug.panelstudio.theme.ThemeTuple;
import com.lukflug.panelstudio.theme.Windows31Theme;
import com.lukflug.panelstudio.widget.ColorPickerComponent;
import com.lukflug.panelstudio.widget.CycleSwitch;
import com.lukflug.panelstudio.widget.DropDownList;
import com.lukflug.panelstudio.widget.ITextFieldKeys;
import com.lukflug.panelstudio.widget.Spinner;
import com.lukflug.panelstudio.widget.ToggleSwitch;

import net.minecraft.util.Formatting;

public class ClickGui extends MinecraftHUDGUI {
    private final GUIInterface inter;
    private final HUDGUI gui;
    public static final int WIDTH=120,HEIGHT=12,DISTANCE=6,BORDER=2;

    public ClickGui() {
        // Getting client structure ...
        IClient client= Module.Category.getClient();
        /* Set to false to disable horizontal clipping, this may cause graphical glitches,
         * but will let you see long text, even if it is too long to fit in the panel. */
        inter=new GUIInterface(true) {
            @Override
            protected String getResourcePrefix() {
                return "bh:";
            }
        };
        // Instantiating theme ...
        ITheme theme=new OptimizedTheme(new ThemeSelector(inter));
        // Instantiating GUI ...
        IToggleable guiToggle=new SimpleToggleable(false);
        IToggleable hudToggle=new SimpleToggleable(false) {
            @Override
            public boolean isOn() {
                return false;
            }
        };
        gui=new HUDGUI(inter,theme.getDescriptionRenderer(),(IPopupPositioner)new MousePositioner(new Point(10,10)),guiToggle,hudToggle);
        // Creating animation ...
        Supplier<Animation> animation=()->new SettingsAnimation(()-> (int)ClickGUIModule.INSTANCE.animationSpeed.getValue(),()->inter.getTime());

        // Creating popup types ...
        BiFunction<Context,Integer,Integer> scrollHeight=(context,componentHeight)->Math.min(componentHeight,Math.max(HEIGHT*4,ClickGui.this.height-context.getPos().y-HEIGHT));
        PopupTuple popupType=new PopupTuple(new PanelPositioner(new Point(0,0)),false,new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return scrollHeight.apply(context,componentHeight);
            }
        });
        PopupTuple colorPopup=new PopupTuple(new CenteredPositioner(()->new Rectangle(new Point(0,0),inter.getWindowSize())),true,new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return scrollHeight.apply(context,componentHeight);
            }
        });
        // Defining resize behavior ...
        IntFunction<IResizable> resizable=width->new IResizable() {
            Dimension size=new Dimension(width,320);

            @Override
            public Dimension getSize() {
                return new Dimension(size);
            }

            @Override
            public void setSize (Dimension size) {
                this.size.width=size.width;
                this.size.height=size.height;
                if (size.width<75) this.size.width=75;
                if (size.height<50) this.size.height=50;
            }
        };
        // Defining scroll behavior ...
        Function<IResizable,IScrollSize> resizableHeight=size->new IScrollSize() {
            @Override
            public int getScrollHeight (Context context, int componentHeight) {
                return size.getSize().height;
            }
        };
        // Defining function keys ...
        IntPredicate keybindKey=scancode->scancode==GLFW.GLFW_KEY_DELETE;
        IntPredicate charFilter=character->{
            return character>=' ';
        };
        ITextFieldKeys keys=new ITextFieldKeys() {
            @Override
            public boolean isBackspaceKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_BACKSPACE;
            }

            @Override
            public boolean isDeleteKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_DELETE;
            }

            @Override
            public boolean isInsertKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_INSERT;
            }

            @Override
            public boolean isLeftKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_LEFT;
            }

            @Override
            public boolean isRightKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_RIGHT;
            }

            @Override
            public boolean isHomeKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_HOME;
            }

            @Override
            public boolean isEndKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_END;
            }

            @Override
            public boolean isCopyKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_C;
            }

            @Override
            public boolean isPasteKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_V;
            }

            @Override
            public boolean isCutKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_X;
            }

            @Override
            public boolean isAllKey (int scancode) {
                return scancode==GLFW.GLFW_KEY_A;
            }
        };

        // Normal generator
        IComponentGenerator generator=new ComponentGenerator(keybindKey,charFilter,keys);
        // Use cycle switches instead of buttons
        IComponentGenerator cycleGenerator=new ComponentGenerator(keybindKey,charFilter,keys) {
            @Override
            public IComponent getEnumComponent (IEnumSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new CycleSwitch(setting,theme.getCycleSwitchRenderer(isContainer));
            }
        };
        // Use all the fancy widgets with text boxes
        IComponentGenerator csgoGenerator=new ComponentGenerator(keybindKey,charFilter,keys) {
            @Override
            public IComponent getBooleanComponent (IBooleanSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new ToggleSwitch(setting,theme.getToggleSwitchRenderer(isContainer));
            }

            @Override
            public IComponent getEnumComponent (IEnumSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new DropDownList(setting,theme,isContainer,false,keys,new IScrollSize(){},adder::addPopup) {
                    @Override
                    protected Animation getAnimation() {
                        return animation.get();
                    }

                    @Override
                    public boolean allowCharacter (char character) {
                        return charFilter.test(character);
                    }

                    @Override
                    protected boolean isUpKey (int key) {
                        return key==GLFW.GLFW_KEY_UP;
                    }

                    @Override
                    protected boolean isDownKey (int key) {
                        return key==GLFW.GLFW_KEY_DOWN;
                    }

                    @Override
                    protected boolean isEnterKey (int key) {
                        return key==GLFW.GLFW_KEY_ENTER;
                    }
                };
            }

            @Override
            public IComponent getNumberComponent (INumberSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new Spinner(setting,theme,isContainer,true,keys);
            }

            @Override
            public IComponent getColorComponent (IColorSetting setting, Supplier<Animation> animation, IComponentAdder adder, ThemeTuple theme, int colorLevel, boolean isContainer) {
                return new ColorPickerComponent(setting,new ThemeTuple(theme.theme,theme.logicalLevel,colorLevel));
            }
        };

        // Classic Panel
        IComponentAdder classicPanelAdder=new PanelAdder(gui,false,()->ClickGUIModule.INSTANCE.layout.getMode()=="classicPanel",title->"classicPanel_"+title) {
            @Override
            protected IResizable getResizable (int width) {
                return resizable.apply(width);
            }

            @Override
            protected IScrollSize getScrollSize (IResizable size) {
                return resizableHeight.apply(size);
            }
        };
        ILayout classicPanelLayout=new PanelLayout(WIDTH,new Point(DISTANCE,DISTANCE),(WIDTH+DISTANCE)/2,HEIGHT+DISTANCE,animation,level->ChildMode.DOWN,level->ChildMode.DOWN,popupType);
        classicPanelLayout.populateGUI(classicPanelAdder,generator,client,theme);

    }

    @Override
    protected HUDGUI getGUI() {
        return gui;
    }

    @Override
    protected GUIInterface getInterface() {
        return inter;
    }

    @Override
    protected int getScrollSpeed() {
        return 5;
    }


    private class ThemeSelector implements IThemeMultiplexer {
        protected Map<ClickGUIModule.Theme,ITheme> themes=new EnumMap<ClickGUIModule.Theme,ITheme>(ClickGUIModule.Theme.class);

        public ThemeSelector (IInterface inter) {
            addTheme(ClickGUIModule.Theme.Clear,new ClearTheme(new ThemeScheme(ClickGUIModule.Theme.Clear),()->true,9,3,1,": "+Formatting.GRAY));
            addTheme(ClickGUIModule.Theme.GameSense,new GameSenseTheme(new ThemeScheme(ClickGUIModule.Theme.GameSense),9,4,5,": "+Formatting.GRAY));
            addTheme(ClickGUIModule.Theme.Rainbow,new RainbowTheme(new ThemeScheme(ClickGUIModule.Theme.Rainbow),()->false,()->false,()->150,9,3,": "+Formatting.GRAY));
            addTheme(ClickGUIModule.Theme.Windows31,new Windows31Theme(new ThemeScheme(ClickGUIModule.Theme.Windows31),9,2,9,": "+Formatting.DARK_GRAY));
            addTheme(ClickGUIModule.Theme.Impact,new ImpactTheme(new ThemeScheme(ClickGUIModule.Theme.Impact),9,4));
        }

        @Override
        public ITheme getTheme() {
            return themes.getOrDefault(ClickGUIModule.INSTANCE.theme.getMode(),themes.get(ClickGUIModule.Theme.GameSense));
        }

        private void addTheme (ClickGUIModule.Theme key, ITheme value) {
            themes.put(key,new OptimizedTheme(value));
            value.loadAssets(inter);
        }


        private class ThemeScheme implements IColorScheme {
            private final ClickGUIModule.Theme themeValue;
            private final String themeName;

            public ThemeScheme (ClickGUIModule.Theme themeValue) {
                this.themeValue=themeValue;
                this.themeName=themeValue.toString().toLowerCase();
            }

            @Override
            public void createSetting (ITheme theme, String name, String description, boolean hasAlpha, boolean allowsRainbow, Color color, boolean rainbow) {
                //ClickGUIModule.INSTANCE.theme.subSettings.add(new ColorSetting(name,themeName+"-"+name,description,()->ClickGUIModule.theme.getValue()==themeValue,hasAlpha,allowsRainbow,color,rainbow));
            }

            @Override
            public Color getColor (String name) {
                return new Color(255,255,255,255);
            }
        }
    }
}