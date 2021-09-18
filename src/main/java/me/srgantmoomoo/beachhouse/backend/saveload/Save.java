package me.srgantmoomoo.beachhouse.backend.saveload;

import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.bedroom.Bedroom;
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
    private File dir;
    private File dataFile;
    ArrayList<String> toSave = new ArrayList<String>();

    public Save() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "beach house");
        if (!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }

        save();
    }

    public void save() {
        saveModules();
        saveGuiPositions();
    }

    public void saveModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
            ArrayList<String> modulesToSave = new ArrayList<>();

            for (Module module : Bedroom.moduleManager.getModules()) {
                if (module.isEnabled() && module.getID() != "clickgui" && module.getID() != "hudeditor") {
                    modulesToSave.add(module.getName());
                }
            }

            for(Module mod : Bedroom.moduleManager.modules) {
                for(Setting setting : mod.settings) {

                    if(setting instanceof BooleanSetting) {
                        BooleanSetting bool = (BooleanSetting) setting;
                        toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + bool.isEnabled());
                    }

                    if(setting instanceof NumberSetting) {
                        NumberSetting number = (NumberSetting) setting;
                        toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + number.getValue());
                    }

                    if(setting instanceof ModeSetting) {
                        ModeSetting mode = (ModeSetting) setting;
                        toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + mode.getMode());
                    }

                    if(setting instanceof ColorSetting) {
                        ColorSetting color = (ColorSetting) setting;
                        //toSave.add("SET:" + mod.getName() + ":" + setting.name + ":" + color.toInteger() + ":" + color.getRainbow());
                    }
                }
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : modulesToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveGuiPositions() {
        try {
            File file = new File(MainDirectory, "guiPositions.txt");
            ArrayList<String> positionsToSave = new ArrayList<>();

            for (Panel panel : ClickGuiScreen.panels) {
                positionsToSave.add(panel.getCategory() + ":" + panel.getX() + ":" + panel.getY() + ":" + panel.isOpen());
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : positionsToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

}
