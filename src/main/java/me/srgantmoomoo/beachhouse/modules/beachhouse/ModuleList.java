package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.Comparator;

import me.srgantmoomoo.beachhouse.events.DrawOverlayEvent;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public class ModuleList extends Module {
	public ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255)); 
	public ModeSetting mode = new ModeSetting("mode", this, "category", "category", "static");
	
	public ModuleList() {
		super("module list", "module list.", 0, Category.COMBAT);
		this.addSettings(color);
	}
	    
	@Override
	public void onEnable() {
		//color.setValue(true, new JColor(255,255,255));    SETS RAINBOW TRUE ON ENABLE.
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	private final Listener<DrawOverlayEvent> listener = new Listener<>(e -> {
		TextRenderer tr = MinecraftClient.getInstance().textRenderer;
		
		int y = 1;
		final int[] counter = { 1 };
		for (Module module : ModuleManager.getModules()) {
			if (module.isEnabled()) {				
				
				JColor colorTr = new JColor(255, 255, 255);
				if(this.mode.is("category")) {
					if(module.getCategory().equals(Category.COMBAT)) colorTr = new JColor(113, 229, 175); //BEACHHOUSE
					if(module.getCategory().equals(Category.MOVEMENT)) colorTr = new JColor(113, 152, 229);
					if(module.getCategory().equals(Category.RENDER)) colorTr = new JColor(229, 106, 113);
					if(module.getCategory().equals(Category.PLAYER)) colorTr = new JColor(227, 229, 103);
					if(module.getCategory().equals(Category.COMBAT)) colorTr = new JColor(122, 103, 229);
					if(module.getCategory().equals(Category.MISCELLANEOUS)) colorTr = new JColor(235, 120, 223);
				}
				
				tr.drawWithShadow(e.matrix, module.getName(), 2, 12 + y, colorTr.getRGB());
				y += tr.fontHeight;
				counter[0]++;
			}
		}
		ModuleManager.modules.sort(Comparator.comparing(module -> -MinecraftClient.getInstance().textRenderer.getWidth(module.getName())));
	});

}