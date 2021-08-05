package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import org.lwjgl.glfw.GLFW;

public class ModuleList extends Module {
	public ColorSetting color = new ColorSetting("color", this, new JColor(172, 172, 172, 255));
	public ModeSetting mode = new ModeSetting("mode", this, "dull", "dull", "vibrant");
	
	public ModuleList() {
		super("module list", "modulelist", "module list.", GLFW.GLFW_KEY_0, Category.BEACHHOUSE);
		this.addSettings(mode);
	}
	    
	@Override
	public void onEnable() {
		//color.setValue(true, new JColor(255,255,255));    SETS RAINBOW TRUE ON ENABLE.
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventDrawOverlay) {
			TextRenderer tr = MinecraftClient.getInstance().textRenderer;

			int y = 1;
			for (Module module : Bedroom.moduleManager.getModules()) {
				if (module.isEnabled()) {
					int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

					JColor colorTr = new JColor(255, 255, 255);
					if(this.mode.is("dull")) {
						if(module.getCategory().equals(Category.BEACHHOUSE)) colorTr = new JColor(113, 229, 175);
						if(module.getCategory().equals(Category.MOVEMENT)) colorTr = new JColor(113, 152, 229);
						if(module.getCategory().equals(Category.RENDER)) colorTr = new JColor(229, 106, 113);
						if(module.getCategory().equals(Category.PLAYER)) colorTr = new JColor(227, 229, 103);
						if(module.getCategory().equals(Category.COMBAT)) colorTr = new JColor(122, 103, 229);
						if(module.getCategory().equals(Category.MISCELLANEOUS)) colorTr = new JColor(235, 120, 223);
					}else if (this.mode.is("vibrant")) {
						if(module.getCategory().equals(Category.BEACHHOUSE)) colorTr = new JColor(255, 255, 255);
						if(module.getCategory().equals(Category.MOVEMENT)) colorTr = new JColor(113, 152, 229);
						if(module.getCategory().equals(Category.RENDER)) colorTr = new JColor(229, 106, 113);
						if(module.getCategory().equals(Category.PLAYER)) colorTr = new JColor(227, 229, 103);
						if(module.getCategory().equals(Category.COMBAT)) colorTr = new JColor(122, 103, 229);
						if(module.getCategory().equals(Category.MISCELLANEOUS)) colorTr = new JColor(235, 120, 223);
					}

					tr.drawWithShadow(((EventDrawOverlay) e).matrix, module.getName(), screenWidth - tr.getWidth(module.getName()) - 1, 1 + y, colorTr.getRGB());
					y += tr.fontHeight;
				}
			}
			//TODO this causes crashes cause of onEvent();
			//Bedroom.moduleManager.getModules().sort(Comparator.comparing(module -> -MinecraftClient.getInstance().textRenderer.getWidth(module.getName())));
		}
	}

}
