package me.srgantmoomoo.beachhouse.feature.command.commands;

import java.util.ArrayList;
import java.util.Comparator;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;

public class ModuleList extends Command {

    public ModuleList() {
        super("modulelist", "gets a list of all the modules.", "moduleList", "ml");
    }
    private ArrayList<Module> mods = new ArrayList<>();

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }
        if(mods.isEmpty()) 
        	mods.addAll(Bedroom.moduleManager.getModules());

        String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
        Bedroom.commandManager.addCustomChatMessage(wuw);
        String nothing = TextFormatting.AQUA + " ";
        Bedroom.commandManager.addCustomChatMessage(nothing);

        for(Module module : mods) {

            if(module.getCategory().equals(Module.Category.PLAYER)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

            if(module.getCategory().equals(Module.Category.RENDER)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

            if(module.getCategory().equals(Module.Category.COMBAT)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

            if(module.getCategory().equals(Module.Category.MOVEMENT)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

            if(module.getCategory().equals(Module.Category.MISCELLANEOUS)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

            if(module.getCategory().equals(Module.Category.BEACHHOUSE)) {
                Bedroom.commandManager.addCustomChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                        (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
            }

        }

        Bedroom.commandManager.addCustomChatMessage(nothing);
        String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
        Bedroom.commandManager.addCustomChatMessage(uwu);

        mods.sort(Comparator.comparing(Module::getCategory));
    }
}