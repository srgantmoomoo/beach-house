package me.srgantmoomoo.bedroom.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

@SuppressWarnings("resource")
public class Help extends Command {
	
	public Help() {
		super("help", "helps u penis.", "help", "h");
	}
	
	public static Prefix prefix;
	public static Toggle toggle;
	public static ModuleList moduleList;

	@Override
	public void onCommand(String[] args, String command) {
		prefix = new Prefix();
		toggle = new Toggle();
		moduleList = new ModuleList();
		
		welcomeMessage();
		helpMessage(prefix.getName(), prefix.getDescription(), prefix.getSyntax());
		helpMessage(toggle.getName(), toggle.getDescription(), toggle.getSyntax());
		helpMessage(moduleList.getName(), moduleList.getDescription(), moduleList.getSyntax());
		goodbyeMessage();
	}
	
	private void helpMessage(String commandName, String commandDesc, String commandSyntax) {
		String starter = TextFormatting.LIGHT_PURPLE + commandName + TextFormatting.GRAY + " - " + commandDesc;
		String syntaxMessage = " [" + CommandManager.prefix + commandSyntax + "]";
		 
		Text textComponentString = new LiteralText(starter);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(textComponentString);
		Text syntaxTextComponentString = new LiteralText(syntaxMessage);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(syntaxTextComponentString);
	}
	
	private void welcomeMessage() {
		String welcomeString = TextFormatting.GRAY + "" + TextFormatting.BOLD + Main.name + " " + Main.version + "!";
		String beachhouse = TextFormatting.GOLD + "@" + TextFormatting.ITALIC + Main.name;
		String nothing = " ";
		
		Text textComponentString = new LiteralText(welcomeString);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(textComponentString);
		Text nothingTextComponentString = new LiteralText(nothing);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(nothingTextComponentString);
		Text beachhouseTextComponentString = new LiteralText(beachhouse);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(beachhouseTextComponentString);
		
	}
	
	private void goodbyeMessage() {
		String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.GOLD + " ~";
		String nothing = " ";
				
		Text nothingTextComponentString = new LiteralText(nothing);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(nothingTextComponentString);
		Text textComponentString = new LiteralText(uwu);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(textComponentString);
	}

}