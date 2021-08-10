package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.command.Commands;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGUI;
import me.srgantmoomoo.beachhouse.module.Modules;
import me.srgantmoomoo.bedroom.Bedroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.beachhouse.gui.InGameUI;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.option.KeyBinding;

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
	public static ClickGUI clickGUI;

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
		printLog("ui initialized.");

		Commands.init();
		printLog("commands initialized.");

		Modules.init();
		printLog("modules initialized.");

		clickGUI = new ClickGUI();
		printLog("clickGui initialized.");

		printLog(Main.name + " has finished initialization.");
	}
}