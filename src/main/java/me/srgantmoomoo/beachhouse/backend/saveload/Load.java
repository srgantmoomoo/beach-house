package me.srgantmoomoo.beachhouse.backend.saveload;

import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.Iterator;

public class Load {
    public File MainDirectory;

    public Load() {
        MainDirectory = new File(MinecraftClient.getInstance().runDirectory, "beach house");
        if (!MainDirectory.exists()) {
            MainDirectory.mkdir();
        }

        load();
    }

    public void load() {
        loadModules();
        loadGui();
    }

    public void loadModules() {
        try {
            File file = new File(MainDirectory, "modules.txt");
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

    public void loadGui() {
        try {
            File file = new File(MainDirectory, "gui.txt");
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

}