package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.*;
import net.minecraft.text.LiteralText;

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

        String inputValue = args[0];
        boolean moduleFound = false;

        List<Module> modules = Bedroom.moduleManager.getModules();

        String nothing = TextFormatting.AQUA + " ";
        String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(wuw));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(nothing));
        for(Module m : modules) {
            String moduleId = m.getID();
            Boolean sent = false;

            if(moduleId.equalsIgnoreCase(inputValue)) {
                for(Setting setting : m.settings) {

                    if(setting instanceof BooleanSetting) {
                        CommandManager.addChatMessage("boolean: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ModeSetting) {
                        CommandManager.addChatMessage("mode: " + TextFormatting.WHITE + setting.name + " " + ((ModeSetting) setting).modes + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof NumberSetting) {
                        CommandManager.addChatMessage("number: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof ColorSetting) {
                        CommandManager.addChatMessage("color: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");
                        sent = true;
                    }

                    if(setting instanceof KeybindSetting) {
                        //CommandManager.addChatMessage("keybind: " + TextFormatting.WHITE + setting.name + TextFormatting.GRAY + ".");  notiing 0_0
                    }
                    if(!sent) {
                        CommandManager.addChatMessage("no settings for this module :(");
                    }

                    moduleFound = true;
                }
                sent = false;
            }
        }
        String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(nothing));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(uwu));

        if(!moduleFound) {
            CommandManager.addChatMessage("module " + TextFormatting.RED + inputValue + TextFormatting.GRAY + " doesnt fucking exist ahhh fuck owwww motherfucker owwuuuch.");
            return;
        }
    }
}