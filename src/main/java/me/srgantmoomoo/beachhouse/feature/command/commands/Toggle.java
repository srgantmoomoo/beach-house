package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;

public class Toggle extends Command {

    public Toggle() {
        super("toggle", "toggles a module by name.", "toggle <module>", "t");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String inputValue = args[0];
        boolean moduleFound = false;
        for(Module module : Bedroom.moduleManager.getModules()) {
            String moudleId = module.getID();
            if(moudleId.equalsIgnoreCase(inputValue)) {
                module.toggle();
                CommandManager.addChatMessage(module.name + " " + (module.isEnabled() ? TextFormatting.GREEN + "enabled" + TextFormatting.GRAY + "." : TextFormatting.DARK_RED + "disabled" + TextFormatting.GRAY + "."));
                moduleFound = true;
                break;
            }
        }
        if(!moduleFound) {
            CommandManager.addChatMessage("the module, " + TextFormatting.RED + inputValue + TextFormatting.GRAY + ", was not found.");
        }
    }
}