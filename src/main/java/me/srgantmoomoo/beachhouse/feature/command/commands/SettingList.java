package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.*;
import net.minecraft.util.Formatting;

import java.util.List;

public class SettingList extends Command {

    public SettingList() {
        super("settinglist", "lists the settings for a given module.", "settinglist <module>", "sl");
    }

    @Override
    public void onCommand(String[] args, String command) {
        String inputValue = args[0];

        if(args.length != 1) {
            Bedroom.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        if(Bedroom.moduleManager.getModuleByID(inputValue) == null) {
            Bedroom.commandManager.addChatMessage("module " + Formatting.RED + inputValue + Formatting.GRAY + " doesnt fucking exist ahhh fuck owwww motherfucker owwuuuch.");
            return;
        }

        List<Module> modules = Bedroom.moduleManager.getModules();
        String nothing = Formatting.AQUA + " ";
        String wuw = Formatting.GRAY + "" + Formatting.BOLD + "wuw" + Formatting.AQUA + " ~";

        Bedroom.commandManager.addCustomChatMessage(wuw);
        Bedroom.commandManager.addCustomChatMessage(nothing);
        for(Module m : modules) {
            String moduleId = m.getID();
            Boolean sent = false;

            if(moduleId.equalsIgnoreCase(inputValue)) {
                for(Setting setting : m.settings) {

                    if(setting instanceof BooleanSetting) {
                        Bedroom.commandManager.addCustomChatMessage("boolean: " + Formatting.WHITE + setting.name + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ModeSetting) {
                        Bedroom.commandManager.addCustomChatMessage("mode: " + Formatting.WHITE + setting.name + " " + ((ModeSetting) setting).modes + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof NumberSetting) {
                        Bedroom.commandManager.addCustomChatMessage("number: " + Formatting.WHITE + setting.name + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ColorSetting) {
                        Bedroom.commandManager.addCustomChatMessage("color: " + Formatting.WHITE + setting.name + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof KeybindSetting) {
                        //Bedroom.commandManager.addChatMessage("keybind: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");  notiing 0_0
                    }
                    if(!sent) {
                        Bedroom.commandManager.addCustomChatMessage("no settings for this module :("); //TODO this wont be needed when keybinds r added... so add keybinds. im just to lazy to do it rn.
                    }
                }
                sent = false;
            }
        }
        String uwu = Formatting.GRAY + "" + Formatting.BOLD + "uwu" + Formatting.AQUA + " ~";
        Bedroom.commandManager.addCustomChatMessage(nothing);
        Bedroom.commandManager.addCustomChatMessage(uwu);
    }
}