package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.util.Formatting;

public class Toggle extends Command {

    public Toggle() {
        super("toggle", "toggles a module by name.", "toggle <module>", "t");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Bedroom.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String inputValue = args[0];
        boolean moduleFound = false;
        for(Module module : Bedroom.moduleManager.getModules()) {
            String moudleId = module.getID();
            if(moudleId.equalsIgnoreCase(inputValue)) {
                module.toggle();
                Bedroom.commandManager.addChatMessage(module.name + " " + (module.isEnabled() ? Formatting.GREEN + "enabled" + Formatting.GRAY + "." : Formatting.DARK_RED + "disabled" + Formatting.GRAY + "."));
                moduleFound = true;
                break;
            }
        }
        if(!moduleFound) {
            Bedroom.commandManager.addChatMessage("the module, " + Formatting.RED + inputValue + Formatting.GRAY + ", was not found.");
        }
    }
}