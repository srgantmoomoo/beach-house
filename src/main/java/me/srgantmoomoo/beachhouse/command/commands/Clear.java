package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.command.Command;

public class Clear extends Command {

	public Clear() {
		super("clear", "clears the chat client side.", "clear", "c");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Reference.minecraft.inGameHud.getChatHud().clear(true);
	}

}
