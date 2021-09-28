package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import me.srgantmoomoo.beachhouse.backend.events.EventRender2d;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.util.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;

public class EnabledModules extends Module {
	public ColorSetting solidColor = new ColorSetting("color", this, new JColor(172, 172, 172, 255));
	public ModeSetting style = new ModeSetting("style", this, "beach", "dull", "vibrant", "beach", "solid", "rainbow");
	public BooleanSetting background = new BooleanSetting("background", this, false);
	public BooleanSetting forgeHax = new BooleanSetting("forgeHax", this, false);
	public BooleanSetting showHidden = new BooleanSetting("showHidden", this, false);

	public EnabledModules() {
		super("enabled modules", "enabledmodules", "enabled stuffysiejsdahjn.", 0, Category.BEACHHOUSE);
		this.addSettings(solidColor, forgeHax, style, background, showHidden);
	}
	private ArrayList<Module> mods = new ArrayList<>();
	private JColor moduleColor = new JColor(255, 255, 255);
	private int maxLength;
	private int size;
	public Module newModule;

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventRender2d) {
			if(mods.isEmpty()) mods.addAll(Bedroom.moduleManager.getModules());

			int screenWidth = Reference.window.getScaledWidth();
			TextRenderer tr = Reference.textRenderer;

			// BACKGROUND
			if(background.isEnabled()) {
				final int[] counterB = {1};

				int outlineColor = 0xff000000;
				if(style.is("vibrant")) outlineColor = 0xffa9a9a9;
				if(style.is("beach")) outlineColor = 0xffa9a9a9;
				if(style.is("rainbow")) outlineColor = rainbow(counterB[0] * 300);

				InGameHud.fill(((EventRender2d) e).matrix, screenWidth - maxLength - 6, 0, screenWidth, size * tr.fontHeight + 6, 0x90000000);
				InGameHud.fill(((EventRender2d) e).matrix, screenWidth - maxLength - 6, 0, screenWidth - maxLength - 5, size * tr.fontHeight + 6, outlineColor);
				InGameHud.fill(((EventRender2d) e).matrix, screenWidth - maxLength - 6, size * tr.fontHeight + 5, screenWidth, size * tr.fontHeight + 6, outlineColor);
				counterB[0]++;
			}
			//InGameHud.fill(((EventRender2d) e).matrix, screenWidth, 0, screenWidth - maxLength - 3, 2, 0x90000000);

			// MODULES
			final int[] counter = {1};
			int y = 0;
			for (Module module : mods) {
				if(!module.isEnabled())
					continue;

				if(!showHidden.isEnabled() && module.getCategory() == Category.BEACHHOUSE)
					continue;

				size = mods.size();

				// constantly checks what the length of the longest module is for the background to draw correctly.
					if (maxLength < tr.getWidth(module.getName())) {
						maxLength = tr.getWidth(module.getName());
						newModule = module;
					}
					if (!newModule.isEnabled()) maxLength = 0;

				// sets the color for the modules.
				if(this.style.is("dull")) {
					colorsAndStuff(module, Category.BEACHHOUSE, 74, 59, 80);
					colorsAndStuff(module, Category.MOVEMENT, 18, 95, 88);
					colorsAndStuff(module, Category.RENDER, 97, 82, 6);
					colorsAndStuff(module, Category.PLAYER, 96, 9, 13);
					colorsAndStuff(module, Category.COMBAT, 74, 59, 80);
					colorsAndStuff(module, Category.MISCELLANEOUS, 51, 102, 153);
				}else if(this.style.is("vibrant")) {
					colorsAndStuff(module, Category.BEACHHOUSE, 255, 39, 42);
					colorsAndStuff(module, Category.MOVEMENT, 102, 255, 0);
					colorsAndStuff(module, Category.RENDER, 0, 255, 255);
					colorsAndStuff(module, Category.PLAYER, 255, 218, 42);
					colorsAndStuff(module, Category.COMBAT, 122, 103, 229);
					colorsAndStuff(module, Category.MISCELLANEOUS, 235, 120, 223);
				}else if (this.style.is("beach")) {
					colorsAndStuff(module, Category.BEACHHOUSE, 113, 229, 175);
					colorsAndStuff(module, Category.MOVEMENT, 113, 152, 229);
					colorsAndStuff(module, Category.RENDER, 229, 106, 113);
					colorsAndStuff(module, Category.PLAYER, 227, 229, 103);
					colorsAndStuff(module, Category.COMBAT, 122, 103, 229);
					colorsAndStuff(module, Category.MISCELLANEOUS, 235, 120, 223);
				}else if(this.style.is("solid")) moduleColor = solidColor.getValue();

				// draws the modules.
				//InGameHud.fill(((EventRender2d) e).matrix, screenWidth, tr.fontHeight + 2 + y, screenWidth - tr.getWidth(module.getName()) -3, tr.fontHeight - (tr.fontHeight) + 2 + y, 0x90000000);
				tr.drawWithShadow(((EventRender2d) e).matrix, module.getName(), screenWidth - tr.getWidth(module.getName()) - 2, y +2, this.style.is("rainbow") ? rainbow(counter[0] * 300) : moduleColor.getRGB());
				y += tr.fontHeight;
				counter[0]++;
			}
			mods.sort(Comparator.comparing(module -> -MinecraftClient.getInstance().textRenderer.getWidth(module.getName())));
		}
	}

	private void colorsAndStuff(Module module, Category category, int r, int g, int b) {
		if(module.getCategory().equals(category)) moduleColor = new JColor(r, g, b);
	}

	private int rainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		rainbowState %= -360;
		return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}
}
