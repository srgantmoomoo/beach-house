package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.CommandLine;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;

public class Clear extends Command {

	public Clear() {
		super("clear", "clears the chat (or console) client side.", "clear", "c");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
			return;
		}

		if(CommandLine.INSTANCE.isInCommandLine)
			CommandLineScreen.outputs.clear();
		else
			Reference.minecraft.inGameHud.getChatHud().clear(true);
	}

}
