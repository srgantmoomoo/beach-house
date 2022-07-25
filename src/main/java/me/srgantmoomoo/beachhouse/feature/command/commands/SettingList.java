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
        if(args.length != 1) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String inputValue = args[0];

        if(Bedroom.INSTANCE.moduleManager.getModuleByID(inputValue) == null) {
            Bedroom.INSTANCE.commandManager.addChatMessage("module " + Formatting.RED + inputValue + Formatting.GRAY + " doesnt fucking exist ahhh fuck owwww motherfucker owwuuuch.");
            return;
        }

        List<Module> modules = Bedroom.INSTANCE.moduleManager.getModules();
        String nothing = Formatting.AQUA + " ";
        String wuw = Formatting.GRAY + "" + Formatting.BOLD + "wuw" + Formatting.AQUA + " ~";

        Bedroom.INSTANCE.commandManager.addCustomChatMessage(wuw);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
        for(Module m : modules) {
            String moduleId = m.getID();
            Boolean sent = false;

            if(moduleId.equalsIgnoreCase(inputValue)) {
                for(Setting setting : m.settings) {

                    if(setting instanceof BooleanSetting) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("boolean: " + Formatting.WHITE + setting.name + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ModeSetting) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("mode: " + Formatting.WHITE + setting.name + " " + ((ModeSetting) setting).getMode() + " " + ((ModeSetting) setting).modes + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof NumberSetting) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("number: " + Formatting.WHITE + setting.name + " " +  ((NumberSetting) setting).getValue() + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ColorSetting) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("color: " + Formatting.WHITE + setting.name + " " + ((ColorSetting) setting).getColor() + Formatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof KeybindSetting) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("keybind: " + Formatting.WHITE + setting.name + Formatting.GRAY + ".");
                    }

                    if(!sent) {
                        Bedroom.INSTANCE.commandManager.addCustomChatMessage("no settings for this module :("); //TODO this wont be needed when keybinds r added... so add keybinds. im just to lazy to do it rn.
                    }
                }
                sent = false;
            }
        }
        String uwu = Formatting.GRAY + "" + Formatting.BOLD + "uwu" + Formatting.AQUA + " ~";
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(uwu);
    }
}