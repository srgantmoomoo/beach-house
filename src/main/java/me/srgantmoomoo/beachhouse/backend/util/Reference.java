package me.srgantmoomoo.beachhouse.backend.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;

public class Reference {
	
	public static final MinecraftClient minecraft = MinecraftClient.getInstance();
	
	public static final ClientPlayerEntity player = minecraft.player;
	public static final ClientWorld world = minecraft.world;
	public static final Window window = minecraft.getWindow();
	
	public static final TextRenderer tr = minecraft.textRenderer;
	
	public static int screenWidth = window.getScaledWidth();
	public static int screenHeight = window.getScaledHeight();

}