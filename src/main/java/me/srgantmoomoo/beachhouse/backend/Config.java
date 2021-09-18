package me.srgantmoomoo.beachhouse.backend;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Config {
    public File MainDirectory;

    public Config() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "beach house");
        if (!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }

        loadConfig();
    }

    public void loadConfig() {
        loadModules();
        //loadSettings();
        loadKeybinds();
        loadClickGuiPositions();
        //loadNotes();
    }

    public void saveConfig() {
        saveModules();
        //saveKeybinds();
        saveClickGuiPositions();
        saveSettings();
        //saveNotes();
    }

    // SAVES

    public void saveModules() {
        try {
            File file = new File(MainDirectory, "ToggledModules.txt");
            ArrayList<String> modulesToSave = new ArrayList<>();

            for (Module module : Bedroom.moduleManager.getModules()) {
                if (module.isEnabled() && module.getID() != "clickgui" && module.getID() != "hudeditor") {
                    modulesToSave.add(module.getName());
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

    public void saveKeybinds() {
        try {
            File file = new File(MainDirectory, "Keybinds.txt");
            ArrayList<String> bindsToSave = new ArrayList<>();

            for (Module module : Bedroom.moduleManager.getModules()) {
                bindsToSave.add(module.getName() + ":" + module.getKey());
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : bindsToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveClickGuiPositions() {
        try {
            File file = new File(MainDirectory, "GuiPanels.txt");
            ArrayList<String> panelsToSave = new ArrayList<>();

            for (Panel panel : ClickGuiScreen.panels) {
                panelsToSave.add(panel.getCategory() + ":" + panel.getX() + ":" + panel.getY() + ":" + panel.isOpen());
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : panelsToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveSettings() {
        //booleans
        try {
            File file = new File(MainDirectory, "BooleanValues.txt");
            ArrayList<String> booleansToSave = new ArrayList<>();
            ArrayList<String> numbersToSave = new ArrayList<>();

            for(Setting setting : Bedroom.settingManager.getSettings()) {
                if(setting instanceof BooleanSetting) {
                    booleansToSave.add(setting.parent.getName() + ":" + setting.name + ":" + ((BooleanSetting) setting).isEnabled());
                }
                /*if(setting instanceof NumberSetting) {
                    numbersToSave.add(setting );
                }
                if(setting instanceof ModeSetting) {

                }
                /*if(setting instanceof ColorSetting) {

                }*/
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : booleansToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }

        // numbers
        try {
            File file = new File(MainDirectory, "BooleanValues.txt");
            ArrayList<String> booleansToSave = new ArrayList<>();

            for (Setting setting : Bedroom.settingManager.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    booleansToSave.add(setting.parent.getName() + ":" + setting.name + ":" + ((BooleanSetting) setting).isEnabled());
                }
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : booleansToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }

        // modes
        try {
            File file = new File(MainDirectory, "ModeValues.txt");
            ArrayList<String> modesToSave = new ArrayList<>();

            for (Setting setting : Bedroom.settingManager.getSettings()) {
                if (setting instanceof ModeSetting) {
                    modesToSave.add(setting.parent.getName() + ":" + setting.name + ":" + ((ModeSetting) setting).getMode());
                }
            }

            try {
                PrintWriter printWriter = new PrintWriter(file);
                for (String string : modesToSave) {
                    printWriter.println(string);
                }
                printWriter.close();
            } catch (FileNotFoundException e) {
            }
        } catch (Exception e) {
        }
    }

    public void saveNotes() {

    }

    // LOADS

    public void loadModules() {
        try {
            File file = new File(MainDirectory, "ToggledModules.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                Iterator var6 = Bedroom.moduleManager.getModules().iterator();

                while (var6.hasNext()) {
                    Module m = (Module) var6.next();
                    if (m.getName().equals(line)) {
                        m.toggle();
                        System.out.println(m.getName() + "penises");
                    }
                }
            }

            br.close();
        } catch (Exception e) {
        }
    }

    public void loadKeybinds() {
        try {
            File file = new File(MainDirectory, "Keybinds.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String bind = curLine.split(":")[1];
                for (Module m : Bedroom.moduleManager.getModules()) {
                    if (m != null && m.getName().equalsIgnoreCase(name)) {
                        m.setKey(Integer.parseInt(bind));
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
        }
    }

    public void loadClickGuiPositions() {
        try {
            File file = new File(MainDirectory, "GuiPanels.txt");
            FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = br.readLine()) != null) {
                String curLine = line.trim();
                String name = curLine.split(":")[0];
                String x = curLine.split(":")[1];
                String y = curLine.split(":")[2];
                String open = curLine.split(":")[3];
                int x1 = Integer.parseInt(x);
                int y1 = Integer.parseInt(y);
                String newName = name;
                if(name.equalsIgnoreCase("BEACHHOUSE")) newName = "beach house";
                boolean opened = Boolean.parseBoolean(open);
                Panel p = ClickGuiScreen.getPanelByName(newName);
                if (p != null) {
                    p.x = x1;
                    p.y = y1;
                    p.setOpen(opened);
                }
            }

            br.close();
        } catch (Exception e) {
        }
    }

    public void loadNotes() {

    }
}