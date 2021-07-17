package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SettingList extends Command {

    public SettingList() {
        super("settinglist", "lists the settings for a given module.", "settinglist <module>", "sl");
    }

    @Override
    public void onCommand(String[] args, String command) {

        if(args.length != 1) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String moduleName = args[0];
        boolean moduleFound = false;

        List<Module> modules = Bedroom.moduleManager.getModules();
        for(Module m : modules) {
            String moduleIn = m.name;
            moduleIn = moduleIn.replaceAll("\\s", "");

            if(moduleIn.equalsIgnoreCase(moduleName)) {
                for(Setting setting : m.settings) {

                    if(setting instanceof BooleanSetting) {
                        CommandManager.addChatMessage("boolean: " + TextFormatting.WHITE + setting.name + ".");
                    }

                    if(setting instanceof ModeSetting) {
                        CommandManager.addChatMessage("mode: " + TextFormatting.WHITE + setting.name + ".");
                    }

                    if(setting instanceof NumberSetting) {
                        CommandManager.addChatMessage("number: " + TextFormatting.WHITE + setting.name + ".");
                    }

                    if(setting instanceof ColorSetting) {
                        CommandManager.addChatMessage("color: " + TextFormatting.WHITE + setting.name + ".");
                    }

                    if(setting instanceof KeybindSetting) {
                        CommandManager.addChatMessage("keybind: " + TextFormatting.WHITE + setting.name + ".");
                    }

                    moduleFound = true;
                }
            }
        }

        if(!moduleFound) {
            CommandManager.addChatMessage("module " + TextFormatting.RED + moduleName + TextFormatting.GRAY + " doesnt fucking exist ahhh fuck owwww motherfucker owwuuuch.");
            return;
        }
    }
}