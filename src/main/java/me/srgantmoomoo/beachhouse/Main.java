package me.srgantmoomoo.beachhouse;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import me.srgantmoomoo.beachhouse.command.Commands;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGui;
import me.srgantmoomoo.beachhouse.gui.hud.HudManager;
import me.srgantmoomoo.beachhouse.module.Modules;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.gui.screen.ChatScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

//TODO chat screen with access transformers.
//TODO rainbow enchant so sexi D:
//TODO fix settingslist when a module does not have any settings... and add a color setting to setting command.
public class Main implements ModInitializer {

	public static final String modid = "bh";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house";
	public static final String version = "0.01";

	public static ClickGui clickGUI;
	public static HudManager hudManager;
	public static ChatScreen chatScreen;

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

		clickGUI = new ClickGui();
		printLog("clickGui initialized.");
		
		hudManager = new HudManager();
		printLog("hud editor initialized.");

		chatScreen = new ChatScreen("chat_screen.title");

		printLog(Main.name + " has finished initialization.");
	}
}