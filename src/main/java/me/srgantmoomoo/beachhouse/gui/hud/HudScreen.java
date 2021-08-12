package me.srgantmoomoo.beachhouse.gui.hud;

import me.srgantmoomoo.beachhouse.Main;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

// this screen is opened in MixinKeyboard currently... //TODO make it a module keybind and changeable.
public class HudScreen extends Screen {

	public HudScreen() {
		super(new LiteralText("bigppgui"));
		
	}
	
	// this renders the hud module when the screen is opened, it doesn't need any events since its already extending Screen.
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		
		for(HudModule m : Main.hudManager.hudModules) {
			m.drawDraggable(matrices, mouseX, mouseY);
		}
		
		super.render(matrices, mouseX, mouseY, delta);
	}
}
