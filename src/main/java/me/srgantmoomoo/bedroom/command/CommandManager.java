package me.srgantmoomoo.bedroom.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.commands.*;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class CommandManager {
	
	public static List<Command> commands = new ArrayList<Command>();
	public static String prefix = ",";
	public boolean commandFound = false;
	
	public CommandManager() {
		Main.EVENTBUS.subscribe(listener);
		register();
	}
	
	public void register() {
		commands.add(new Toggle());
		commands.add(new Help());
		commands.add(new Prefix());
		commands.add(new ModuleList());
	}
	
	public static void callCommandReturn(String input) {
        String message = input;
        
        if(!message.startsWith(prefix))
        	return;
        
        message = message.substring(prefix.length());
        if(message.split(" ").length > 0) {
        	boolean commandFound = false;
        	String commandName = message.split(" ")[0];
        	for(Command c : commands) {
        		if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
	        		c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
	        		commandFound = true;
	        		break;
        		}
        	}
        	if(!commandFound) {
        		addChatMessage(TextFormatting.DARK_RED + "command does not exist, use " + TextFormatting.ITALIC + prefix + "help " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + "for help.");
        	}
        }
    }
	
	@EventHandler
	private final Listener<EventKeyPress> listener = new Listener<>(e -> {
		if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), prefix.charAt(0)))
		if (prefix.length() == 1) {
                MinecraftClient.getInstance().openScreen(new ChatScreen(""));
            }
	});

	public static void setCommandPrefix(String pre) {
        prefix = pre;
        
        if(Main.saveLoad != null) {
			Main.saveLoad.save();
		}
    }
	
	@SuppressWarnings("resource")
	public static void addChatMessage(String message) {
		String messageWithPre = TextFormatting.GOLD + "@" + TextFormatting.ITALIC + Main.name + TextFormatting.GRAY + ": " + message;
		Text textComponentString = new LiteralText(messageWithPre);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(textComponentString);
	}
	
	@SuppressWarnings("resource")
	public static void correctUsageMsg(String name, String syntax) {
		String usage = TextFormatting.RED + "correct usage of " + name + " command -> " + TextFormatting.GRAY + prefix + syntax;
		String message = TextFormatting.GOLD + "@" + TextFormatting.ITALIC + Main.name + TextFormatting.GRAY + ": " + usage;
		
		Text textComponentString = new LiteralText(message);
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(textComponentString);
	}
	
}