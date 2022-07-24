package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.backend.UuidChecker;
import me.srgantmoomoo.beachhouse.backend.saveload.Load;
import me.srgantmoomoo.beachhouse.backend.saveload.Save;
import me.srgantmoomoo.beachhouse.feature.command.Commands;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.beachhouse.gui.hud.HudManager;
import me.srgantmoomoo.beachhouse.feature.module.Modules;
import me.srgantmoomoo.beachhouse.feature.notepad.NotepadManager;
import me.srgantmoomoo.beachhouse.gui.options.OptionsScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

//TODO fix color button & options menu in general.
//TODO arraylist in hud modules.
//TODO ,sl causes crash.

//TODO font renderer.
//TODO rainbow enchant so sexi D:.
//TODO animations.

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

//TODO command line error msg. camera zoom module.
//TODO bedroom.
//TODO xray.
//TODO resets.

//TODO website.
//TODO discord rpc.

//TODO command line stuff.
//TODO scrolling in command line and top down option.

//TODO seaweed fucks with jesus module.

public class Main implements ModInitializer {

	public static final String modid = "beachhouse";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house";
	public static final boolean checkUuids = false;
	public static final String version = "0.11";

	public static ClickGuiScreen clickGui;
	public static HudManager hudManager;
	public static CommandLineScreen commandLine;
	public static OptionsScreen options;
	public static NotepadManager notepadManager;
	public static Save save;
	public static Load load;
	public static UuidChecker nameChecker;

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
		nameChecker = new UuidChecker();
	}

	public void beachhouseInit() {
		Font[] fonts;
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font font : fonts) {
			System.out.print(font.getFontName() + " : ");
			System.out.print(font.getFamily() + " : ");
			System.out.print(font.getName());
			System.out.println();
		}

		Commands.init();
		printLog("commands initialized.");

		Modules.init();
		printLog("modules initialized.");

		clickGui = new ClickGuiScreen();
		printLog("click gui initialized.");

		hudManager = new HudManager();
		printLog("hud editor initialized.");

		commandLine = new CommandLineScreen();
		printLog("command line initialized.");

		options = new OptionsScreen();
		printLog("options screen initialized");

		notepadManager = new NotepadManager();
		printLog("big pp notepad manager is initialized.");

		save = new Save();
		load = new Load();
		printLog("saves and loads initialized.");

		printLog(Main.name + " has finished initialization.");
	}
}
