package me.srgantmoomoo.beachhouse.backend.saveload;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.feature.notepad.Notepad;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
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

    public Save() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "beach house");
        if(!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }
    }

    public void save() {
        saveModules();
        saveSettings();
        saveGui();
        saveHud();
        saveNotepad();
        savePrefix();
    }

    private void writeFile(ArrayList<String> toSave, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for(String string : toSave) {
                printWriter.println(string);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
        }
    }

    public void saveModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
            ArrayList<String> toSave = new ArrayList<>();

            for(Module module : Bedroom.moduleManager.getModules()) {
                if (module.isEnabled() && module.getID() != "clickgui" && module.getID() != "hudeditor" && module.getID() != "commandline") {
                    toSave.add(module.getName());
                }
            }

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

    public void saveSettings() {
        try {
            File file = new File(MainDirectory, "settings.txt");
            ArrayList<String> toSave = new ArrayList<>();

            for(Module mod : Bedroom.moduleManager.modules) {
                for(Setting setting : mod.settings) {

                    if(setting instanceof BooleanSetting) {
                        BooleanSetting bool = (BooleanSetting) setting;
                        toSave.add(setting.parent.getName() + ":" + setting.name + ":" + bool.isEnabled());
                    }

                    if(setting instanceof NumberSetting) {
                        NumberSetting number = (NumberSetting) setting;
                        toSave.add(setting.parent.getName() + ":" + setting.name + ":" + number.getValue());
                    }

                    if(setting instanceof ModeSetting) {
                        ModeSetting mode = (ModeSetting) setting;
                        toSave.add(setting.parent.getName() + ":" + setting.name + ":" + mode.getMode());
                    }

                    if(setting instanceof ColorSetting) {
                        ColorSetting color = (ColorSetting) setting;
                        //toSave.add(setting.parent.getName() + ":" + setting.name + ":" + color.toInteger() + ":" + color.getRainbow());
                    }
                }
            }

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

    public void saveGui() {
        try {
            File file = new File(MainDirectory, "gui.txt");
            ArrayList<String> toSave = new ArrayList<>();

            for(Panel panel : Main.clickGui.panels) {
                toSave.add(panel.getCategory() + ":" + panel.getX() + ":" + panel.getY() + ":" + panel.isOpen());
            }

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

    public void saveHud() {
        try {
            File file = new File(MainDirectory, "hud.txt");
            ArrayList<String> toSave = new ArrayList<>();

            for(HudModule hud : Main.hudManager.hudModules) {
                toSave.add(hud.getName() + ":" + hud.getX() + ":" + hud.getY() + ":" + hud.isHudEnabled());
            }

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

    public void saveNotepad() {
        try {
            File file = new File(MainDirectory, "notepad.txt");
            ArrayList<String> toSave = new ArrayList<>();

            for(Notepad note : Main.notepadManager.getNotes()) {
                toSave.add(note.getName() + ":" + note.getMessage());
            }

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

    public void savePrefix() {
        try {
            File file = new File(MainDirectory, "prefix.txt");
            ArrayList<String> toSave = new ArrayList<>();

            toSave.add(Bedroom.commandManager.prefix);

            writeFile(toSave, file);
        } catch (Exception e) {
        }
    }

}
