package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.backend.saveload.Load;
import me.srgantmoomoo.beachhouse.backend.saveload.Save;
import me.srgantmoomoo.beachhouse.feature.command.Commands;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
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

//TODO settings in config.
//TODO hudeditor in config

//TODO font renderer.
//TODO rainbow enchant so sexi D:
//TODO animations.

//TODO add a color setting to setting command.
//TODO command pages.
//TODO add a bind command.

//TODO if(settings (opened)) *** focused settings.
//TODO overlapping panels in general.
//TODO gui code can be improved on a lot. make single method for rendering with the onwall shit.
//TODO get number setting to work with onwall.
//TODO if(clicked out) than setting closes.
//TODO focusing for the panels.
//TODO clickgui hover for specific modules.

public class Main implements ModInitializer {

	public static final String modid = "bh";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house";
	public static final String version = "0.10";

	public static ClickGuiScreen clickGui;
	public static HudManager hudManager;
	public static Save save;
	public static Load load;
	public static NotepadManager notepadManager;

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