package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.CommandLine;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;

public class CheckModules extends Command {

    public CheckModules() {
        super("checkmodules", "checks for the current working modules.", "checkmodules", "cm");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 0) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        Bedroom.INSTANCE.commandManager.addChatMessage("jesus, player velocity, sprint, entity esp, full bright, render cancel, vibrant shader, fly, fast place, anti hunger, and packet cancel.");
    }
}
