package me.srgantmoomoo.bedroom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.SettingManager;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public final class Bedroom {

    public String modid;
    public String modname;
    public String modversion;

    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public CommandManager commandManager;

    public static final Logger LOGGER = LogManager.getLogger("bedroom");

    public static final Object syncronize = new Object();
    public static void printLog(String text) {
        synchronized (syncronize) {
            LOGGER.info(text);
        }
    }

    public static Bedroom INSTANCE;

    public Bedroom() {
        INSTANCE = this;
    }

    public void init(String id, String name, String version) {
        printLog("welcome to bedroom!");
        printLog("\n" +
                " __                     __                                       \n" +
                "[  |                   |  ]                                      \n" +
                " | |.--.   .---.   .--.| |  _ .--.   .--.    .--.   _ .--..--.   \n" +
                " | '/'`\\ \\/ /__\\\\/ /'`\\' | [ `/'`\\]/ .'`\\ \\/ .'`\\ \\[ `.-. .-. |  \n" +
                " |  \\__/ || \\__.,| \\__/  |  | |    | \\__. || \\__. | | | | | | |  \n" +
                "[__;.__.'  '.__.' '.__.;__][___]    '.__.'  '.__.' [___||__||__] \n");

        modid = id;
        modname = name;
        modversion = version;
        printLog("variables initialized.");

        commandManager = new CommandManager();
        printLog("command system initialized.");

        moduleManager = new ModuleManager();
        printLog("module system initialized.");

        settingManager = new SettingManager();
        printLog("setting system initialized.");
    }

    public void addModule(Module module) {
        moduleManager.modules.add(module);
    }

    public void addCommand(Command command) {
        commandManager.commands.add(command);
    }

}
