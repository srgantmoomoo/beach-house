package me.srgantmoomoo.beachhouse.backend.util;

import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;

public class Reference {

	public static final MinecraftClient minecraft = MinecraftClient.getInstance();

	public static final ClientPlayerEntity player = minecraft.player;
	public static final ClientWorld world = minecraft.world;
	public static final Window window = minecraft.getWindow();

	public static final TextRenderer textRenderer = minecraft.textRenderer;

	public static final ManagedShaderEffect blur = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/blur" + ".json"));
	public static final ManagedShaderEffect art = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/art" + ".json"));
	public static final ManagedShaderEffect vibrant = ShaderEffectManager.getInstance().manage(new Identifier("minecraft", "shaders/post/color_convolve" + ".json"));
}
