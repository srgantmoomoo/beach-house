package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;

//TODO this currently ignores the color system with ColorSetting... so maybe implement that eventually.
public class ModuleList extends Module {
	public ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255));
	public ModeSetting style = new ModeSetting("style", this, "dull", "dull", "vibrant", "rainbow");
	public BooleanSetting background = new BooleanSetting("background", this, false);
	
	public ModuleList() {
		super("module list", "modulelist", "module list.", 0, Category.BEACHHOUSE);
		this.addSettings(style, background);
	}
	private ArrayList<Module> mods = new ArrayList<>();
	private JColor moduleColor = new JColor(255, 255, 255);
	public int maxLength = 1;
	public Module newModule;
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public void onEvent(Event e) {
		if(e instanceof DrawOverlayEvent) {
			if(mods.isEmpty()) mods.addAll(Bedroom.moduleManager.getModules());
			
			int screenWidth = Reference.window.getScaledWidth();
			TextRenderer tr = Reference.tr;
			
			// BACKGROUND
			if(background.isEnabled()) {
				final int[] counterB = {1};
				int size  = Bedroom.moduleManager.getEnabledModules().size();
				
				int outlineColor = 0xffffffff;
				if(style.is("vibrant")) outlineColor = 0xffff00ff;
				if(style.is("rainbow")) outlineColor = rainbow(counterB[0] * 300);
				
				InGameHud.fill(((DrawOverlayEvent) e).matrix, screenWidth - maxLength - 6, 0, screenWidth, size * tr.fontHeight + 6, 0x90000000);
				InGameHud.fill(((DrawOverlayEvent) e).matrix, screenWidth - maxLength - 6, 0, screenWidth - maxLength - 5, size * tr.fontHeight + 6, outlineColor);
				InGameHud.fill(((DrawOverlayEvent) e).matrix, screenWidth - maxLength - 6, size * tr.fontHeight + 5, screenWidth, size * tr.fontHeight + 6, outlineColor);
				counterB[0]++;
			}
			
			// MODULES
			final int[] counter = {1};
			int y = 1;
			for (Module module : mods) {
				if (module.isEnabled()) {
					
					// constantly checks what the length of the longest module is for the background to draw correctly.
					if(maxLength < tr.getWidth(module.getName())) {
						maxLength = tr.getWidth(module.getName());
						newModule = module;
					}
					if(!newModule.isEnabled()) maxLength = 0;
					
					// sets the color for the modules.
					if(this.style.is("dull")) {
						if(module.getCategory().equals(Category.BEACHHOUSE)) moduleColor = new JColor(113, 229, 175);
						if(module.getCategory().equals(Category.MOVEMENT)) moduleColor = new JColor(113, 152, 229);
						if(module.getCategory().equals(Category.RENDER)) moduleColor = new JColor(229, 106, 113);
						if(module.getCategory().equals(Category.PLAYER)) moduleColor = new JColor(227, 229, 103);
						if(module.getCategory().equals(Category.COMBAT)) moduleColor = new JColor(122, 103, 229);
						if(module.getCategory().equals(Category.MISCELLANEOUS)) moduleColor = new JColor(235, 120, 223);
					}else if(this.style.is("vibrant")) {
						if(module.getCategory().equals(Category.BEACHHOUSE)) moduleColor = new JColor(255, 255, 255);
						if(module.getCategory().equals(Category.MOVEMENT)) moduleColor = new JColor(113, 152, 229);
						if(module.getCategory().equals(Category.RENDER)) moduleColor = new JColor(229, 106, 113);
						if(module.getCategory().equals(Category.PLAYER)) moduleColor = new JColor(227, 229, 103);
						if(module.getCategory().equals(Category.COMBAT)) moduleColor = new JColor(122, 103, 229);
						if(module.getCategory().equals(Category.MISCELLANEOUS)) moduleColor = new JColor(235, 120, 223);
					}
					
					// draws the modules.
					tr.drawWithShadow(((DrawOverlayEvent) e).matrix, module.getName(), screenWidth - tr.getWidth(module.getName()) - 1, 1 + y, this.style.is("rainbow") ? rainbow(counter[0] * 300) : moduleColor.getRGB());
					y += tr.fontHeight;
					counter[0]++;
				}
			}
			mods.sort(Comparator.comparing(module -> -MinecraftClient.getInstance().textRenderer.getWidth(module.getName())));
		}
	}
	
	private int rainbow(int delay) {
		   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		   rainbowState %= -360;
	       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}
}
