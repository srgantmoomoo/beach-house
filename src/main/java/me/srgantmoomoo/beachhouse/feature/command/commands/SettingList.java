package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.*;

import java.util.List;

public class SettingList extends Command {

    public SettingList() {
        super("settinglist", "lists the settings for a given module.", "settinglist <module>", "sl");
    }

    @Override
    public void onCommand(String[] args, String command) {

        if(args.length != 1) {
            Bedroom.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String inputValue = args[0];
        boolean moduleFound = false;

        List<Module> modules = Bedroom.moduleManager.getModules();

        String nothing = TextFormatting.AQUA + " ";
        String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
        Bedroom.commandManager.addCustomChatMessage(wuw);
        Bedroom.commandManager.addCustomChatMessage(nothing);
        for(Module m : modules) {
            String moduleId = m.getID();
            Boolean sent = false;

            if(moduleId.equalsIgnoreCase(inputValue)) {
                for(Setting setting : m.settings) {

                    if(setting instanceof BooleanSetting) {
                        Bedroom.commandManager.addCustomChatMessage("boolean: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ModeSetting) {
                        Bedroom.commandManager.addCustomChatMessage("mode: " + TextFormatting.WHITE + setting.name + " " + ((ModeSetting) setting).modes + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof NumberSetting) {
                        Bedroom.commandManager.addCustomChatMessage("number: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ColorSetting) {
                        Bedroom.commandManager.addCustomChatMessage("color: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof KeybindSetting) {
                        //Bedroom.commandManager.addChatMessage("keybind: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");  notiing 0_0
                    }
                    if(!sent) {
                        Bedroom.commandManager.addCustomChatMessage("no settings for this module :(");
                    }

                    moduleFound = true;
                }
                sent = false;
            }
        }
        String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
        Bedroom.commandManager.addCustomChatMessage(nothing);
        Bedroom.commandManager.addCustomChatMessage(uwu);

        if(!moduleFound) {
            Bedroom.commandManager.addChatMessage("module " + TextFormatting.RED + inputValue + TextFormatting.GRAY + " doesnt fucking exist ahhh fuck owwww motherfucker owwuuuch.");
            return;
        }
    }
}