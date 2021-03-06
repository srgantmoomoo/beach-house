package me.srgantmoomoo.beachhouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.beachhouse.api.config.SaveLoad;
import me.srgantmoomoo.beachhouse.api.event.Event;
import me.srgantmoomoo.beachhouse.api.event.EventProcessor;
import me.srgantmoomoo.beachhouse.module.ModuleManager;
import me.srgantmoomoo.beachhouse.setting.SettingManager;
import me.srgantmoomoo.beachhouse.ui.UI;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class Main implements ModInitializer {
	
	public static final String modid = "bh";
	public static final String name = "beach house";
	public static final String nameCondensed = "beach-house";
	public static final String version = "0.0.1";
	
	public static final Logger LOGGER = LogManager.getLogger("beach-house");
	private MinecraftClient mc = MinecraftClient.getInstance();
	public static EventBus EVENTBUS = new EventManager();
	
	public static UI ui;
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	
	@Override
	public void onInitialize() {
		eventProcessor = new EventProcessor();
		Main.EVENTBUS.subscribe(eventProcessor);
		
		ui = new UI();
		
		moduleManager = new ModuleManager();
		
		settingManager = new SettingManager();
		
		saveLoad = new SaveLoad();
		
		LOGGER.info("\n" +
                " __                             __        __                                    \n" +
                "[  |                           [  |      [  |                                   \n" +
                " | |.--.   .---.  ,--.   .---.  | |--.    | |--.   .--.   __   _   .--.  .---.  \n" +
                " | '/'`\\ \\/ /__\\\\`'_\\ : / /'`\\] | .-. |   | .-. |/ .'`\\ \\[  | | | ( (`\\]/ /__\\\\ \n" +
                " |  \\__/ || \\__.,// | |,| \\__.  | | | |   | | | || \\__. | | \\_/ |, `'.'.| \\__., \n" +
                "[__;.__.'  '.__.'\\'-;__/'.___.'[___]|__] [___]|__]'.__.'  '.__.'_/[\\__) )'.__.' \n" +
                "                                                                                \n");
		
		
		/*
		" __                             __        __                                    \n" +
		"[  |                           [  |      [  |                                   \n" +
		" | |.--.   .---.  ,--.   .---.  | |--.    | |--.   .--.   __   _   .--.  .---.  \n" +
		" | '/'`\ \/ /__\\`'_\ : / /'`\] | .-. |   | .-. |/ .'`\ \[  | | | ( (`\]/ /__\\ \n" +
		" |  \__/ || \__.,// | |,| \__.  | | | |   | | | || \__. | | \_/ |, `'.'.| \__., \n" +
		"[__;.__.'  '.__.'\'-;__/'.___.'[___]|__] [___]|__]'.__.'  '.__.'_/[\__) )'.__.' \n");
		*/                                                                

        LOGGER.info("loading beach house...");
	}

}