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
import me.srgantmoomoo.beachhouse.gui.IngameUI;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.fabricmc.api.ModInitializer;

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
	
	public static IngameUI ingameUI;
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	public static CommandManager commandManager;
	
	public final Object syncronize = new Object();
	public void printLog(String text) {
		synchronized (syncronize) {
			LOGGER.info(text);
		}
	}
	
	@Override
	public void onInitialize() {
		bedroomInit();
		beachhouseInit();
	}
	
	public void bedroomInit() {
		printLog("welcome to bedroom!");
		printLog("\n" +
                " __                     __                                       \n" +
                "[  |                   |  ]                                      \n" +
                " | |.--.   .---.   .--.| |  _ .--.   .--.    .--.   _ .--..--.   \n" +
                " | '/'`\\ \\/ /__\\\\/ /'`\\' | [ `/'`\\]/ .'`\\ \\/ .'`\\ \\[ `.-. .-. |  \n" +
                " |  \\__/ || \\__.,| \\__/  |  | |    | \\__. || \\__. | | | | | | |  \n" +
                "[__;.__.'  '.__.' '.__.;__][___]    '.__.'  '.__.' [___||__||__] \n");
		
		eventProcessor = new EventProcessor();
		printLog("event system initialized.");
		
		commandManager = new CommandManager();
		printLog("command system initialized.");
		
		moduleManager = new ModuleManager();
		printLog("module system initialized.");
		
		settingManager = new SettingManager();
		printLog("setting system initialized.");

		saveLoad = new SaveLoad();
		printLog("config initialized.");
		
		printLog("bedroom" + " has finished initialization :)");
	}
	
	public void beachhouseInit() {
		Font[] fonts;
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (int i = 0; i < fonts.length; i++) {
		      System.out.print(fonts[i].getFontName() + " : ");
		      System.out.print(fonts[i].getFamily() + " : ");
		      System.out.print(fonts[i].getName());
		      System.out.println();
		}

		ingameUI = new IngameUI();
		printLog("ui initialized.");
		
		printLog(Main.name + " has finished initialization.");
	}
}