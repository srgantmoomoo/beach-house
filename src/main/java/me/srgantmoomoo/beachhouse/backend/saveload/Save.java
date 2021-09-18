package me.srgantmoomoo.beachhouse.backend.saveload;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save {
    public File MainDirectory;

    public Save() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "beach house");
        if (!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }
    }

    public void save() {
        saveModules();
        saveGui();
        saveNotepad();
        saveCommandPrefix();
    }

    public void saveModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
            ArrayList<String> moduleToSave = new ArrayList<>();

            for (Module module : Bedroom.moduleManager.getModules()) {
                if (module.isEnabled() && module.getID() != "clickgui" && module.getID() != "hudeditor") {
                    moduleToSave.add(module.getName());
                }
            }

            /*for(Module mod : Bedroom.moduleManager.modules) {
                for(Setting setting : mod.settings) {

                    if(setting instanceof BooleanSetting) {
                        BooleanSetting bool = (BooleanSetting) setting;
                        modulesToSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
                    }

                    if(setting instanceof NumberSetting) {
                        NumberSetting number = (NumberSetting) setting;
                        modulesToSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + number.getValue());
                    }

                    if(setting instanceof ModeSetting) {
                        ModeSetting mode = (ModeSetting) setting;
                        modulesToSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
                    }

                    if(setting instanceof ColorSetting) {
                        ColorSetting color = (ColorSetting) setting;
                        //toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + color.toInteger() + ":" + color.getRainbow());
                    }
                }
            }*/

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : moduleToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveGui() {
        try {
            File file = new File(MainDirectory, "gui.txt");
            ArrayList<String> guiToSave = new ArrayList<>();

            for (Panel panel : Main.clickGui.panels) {
                guiToSave.add(panel.getCategory() + ":" + panel.getX() + ":" + panel.getY() + ":" + panel.isOpen());
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : guiToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveNotepad() {

    }

    public void saveCommandPrefix() {
        try {
            File file = new File(MainDirectory, "prefix.txt");
            ArrayList<String> prefixToSave = new ArrayList<>();

            prefixToSave.add(Bedroom.commandManager.prefix);

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : prefixToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

}
