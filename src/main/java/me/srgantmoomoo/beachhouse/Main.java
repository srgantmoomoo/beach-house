package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.backend.NameChecker;
import me.srgantmoomoo.beachhouse.backend.saveload.Load;
import me.srgantmoomoo.beachhouse.backend.saveload.Save;
import me.srgantmoomoo.beachhouse.feature.command.Commands;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.beachhouse.gui.hud.HudManager;
import me.srgantmoomoo.beachhouse.feature.module.Modules;
import me.srgantmoomoo.beachhouse.feature.notepad.NotepadManager;
import me.srgantmoomoo.bedroom.Bedroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

//TODO font renderer.
//TODO rainbow enchant so sexi D:.
//TODO animations.

//TODO add a color setting to setting command.
//TODO add a bind command.

//TODO if(settings (opened)) *** focused settings.
//TODO overlapping panels in general.
//TODO get number setting to work with onwall.
//TODO if(clicked out) than setting closes.
//TODO focusing for the panels.

//TODO finish session info hud module.
//TODO add player model and other hud modules.

//TODO notifications.
//TODO radar hud module.
//TODO ADA graph.

//TODO figuerw out why that doesn't work (commands line & camera zoom).
//TODO scrolling in command line and top down option.
//TODO get settings in the hud.
//TODO bedroom.
//TODO xray.
//TODO resets.
//TODO binds in config.

//TODO website.
//TODO discord rpc.
//TODO redo watermark style1 to fit new logo/

public class Main implements ModInitializer {

	public static final String modid = "bh";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house";
	public static final boolean checkNames = false;
	public static final String version = "0.10";

	public static ClickGuiScreen clickGui;
	public static HudManager hudManager;
	public static CommandLineScreen commandLine;
	public static Save save;
	public static Load load;
	public static NotepadManager notepadManager;
	public static NameChecker nameChecker;

	public static final Logger LOGGER = LogManager.getLogger("beach house");

	public final Object syncronize = new Object();
	public void printLog(String text) {
		synchronized (syncronize) {
			LOGGER.info(text);
		}
	}

	@Override
	public void onInitialize() {
		Bedroom.init(modid, name, version);
		beachhouseInit();
		//Discord.startRPC(); //TODO fix this
		nameChecker = new NameChecker();
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

		Commands.init();
		printLog("commands initialized.");

		Modules.init();
		printLog("modules initialized.");

		clickGui = new ClickGuiScreen();
		printLog("click gui initialized.");

		commandLine = new CommandLineScreen();
		printLog("command line initialized.");
		
		hudManager = new HudManager();
		printLog("hud editor initialized.");

		notepadManager = new NotepadManager();
		printLog("big pp notepad manager is initialized.");

		save = new Save();
		load = new Load();
		printLog("saves and loads initialized.");

		printLog(Main.name + " has finished initialization.");
	}
}