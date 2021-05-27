package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.bedroom.api.config.SaveLoad;
import me.srgantmoomoo.bedroom.api.event.EventProcessor;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.SettingManager;
import me.srgantmoomoo.bedroom.ui.UI;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class Main implements ModInitializer {
	
	public static final String modid = "bh";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house"; 
	public static final String version = "0.01";
	
	public static final Logger LOGGER = LogManager.getLogger("bedroom");
	public static EventBus EVENTBUS = new EventManager();
	
	public static UI ui;
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	public static CommandManager commandManager;
	
	public Object syncronize = new Object();
	public void printLog(String text) {
		synchronized (syncronize) {
			LOGGER.info(text);
		}
	}
	
	@Override
	public void onInitialize() {
		printLog("welcome to bedroom!");
		
		printLog("\n" +
                " __                     __                                       \n" +
                "[  |                   |  ]                                      \n" +
                " | |.--.   .---.   .--.| |  _ .--.   .--.    .--.   _ .--..--.   \n" +
                " | '/'`\\ \\/ /__\\\\/ /'`\\' | [ `/'`\\]/ .'`\\ \\/ .'`\\ \\[ `.-. .-. |  \n" +
                " |  \\__/ || \\__.,| \\__/  |  | |    | \\__. || \\__. | | | | | | |  \n" +
                "[__;.__.'  '.__.' '.__.;__][___]    '.__.'  '.__.' [___||__||__] \n");
		
		// bedroom inits
		
		Font[] fonts;
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (int i = 0; i < fonts.length; i++) {
		      System.out.print(fonts[i].getFontName( ) + " : ");
		      System.out.print(fonts[i].getFamily( ) + " : ");
		      System.out.print(fonts[i].getName( ));
		      System.out.println( );
		}
		
		
		commandManager = new CommandManager();
		printLog("command system initialized.");
		
		moduleManager = new ModuleManager();
		printLog("module system initialized.");
		
		settingManager = new SettingManager();
		printLog("setting system initialized.");

		ui = new UI();
		printLog("ui initialized.");
		
		saveLoad = new SaveLoad();
		printLog("config initialized.");
		
		eventProcessor = new EventProcessor();
		printLog("event system initialized.");
		
		printLog("bedroom" + " has finished initialization :)");
		
		// (your clients name)'s inits... if u need any.
		
		//
		
		printLog(Main.name + " has finished initialization.");
	}
}