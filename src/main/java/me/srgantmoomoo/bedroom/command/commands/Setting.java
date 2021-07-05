package me.srgantmoomoo.bedroom.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;

public class Setting extends Command {

    public Setting() {
        super("setting", "allows you to change settings of modules.", "setting <module> <setting> <value>", "s");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 3) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String moduleName = args[0];
        Module module = Main.moduleManager.getModule(moduleName);

        if(module == null) {
            CommandManager.addChatMessage("the module " + moduleName + " does not exist dumfuck.");
            return;
        }
    }
}
