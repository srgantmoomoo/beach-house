package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.command.Commands;
import me.srgantmoomoo.beachhouse.module.Modules;
import me.srgantmoomoo.bedroom.Bedroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.bedroom.api.config.SaveLoad;
import me.srgantmoomoo.bedroom.api.event.EventProcessor;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.SettingManager;
import me.srgantmoomoo.beachhouse.gui.InGameUI;
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

	public static InGameUI inGameUI;

	public static final Logger LOGGER = LogManager.getLogger("beach house");
	
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
		Bedroom.init(modid, name, version);
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

		inGameUI = new InGameUI();
		Bedroom.EVENTBUS.subscribe(inGameUI);
		printLog("ui initialized.");

		Commands.init();
		printLog("commands initialized.");

		Modules.init();
		printLog("modules initialized.");
		
		printLog(Main.name + " has finished initialization.");
	}
}