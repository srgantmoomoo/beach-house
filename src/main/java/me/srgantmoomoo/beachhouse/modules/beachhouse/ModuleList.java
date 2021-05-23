package me.srgantmoomoo.beachhouse.modules.beachhouse;

import java.util.Comparator;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import me.srgantmoomoo.bedroom.setting.settings.ColorSetting;
import me.srgantmoomoo.external.renderer.FontRenderer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.font.TextRenderer;

public class ModuleList extends Module {
	public ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255)); 
	
	public ModuleList() {
		super("module list", "module list.", 0, Category.BEACHHOUSE);
	}
	
	@Override
	public void onEnable() {
		Main.EVENTBUS.subscribe(listener);
		color.setValue(true, new JColor(255,255,255));
	}
	
	@Override
	public void onDisable() {
		Main.EVENTBUS.unsubscribe(listener);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		TextRenderer tr = mc.textRenderer;
		FontRenderer fr = Main.fontRenderer;
		
		int y = 1;
		final int[] counter = { 1 };
		for (Module module : ModuleManager.getModules()) {
			if (module.isEnabled()) {				
				fr.drawString(e.matrix, module.getName(), 1, 12 + y, true, color.getValue());
				y += tr.fontHeight;
				counter[0]++;
			}
		}
		ModuleManager.modules.sort(Comparator.comparing(module -> -mc.textRenderer.getWidth(module.getName())));
	});

}
