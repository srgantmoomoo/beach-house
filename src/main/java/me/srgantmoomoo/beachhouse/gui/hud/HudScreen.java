package me.srgantmoomoo.beachhouse.gui.hud;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

// this screen is opened in the HudEditor module.
public class HudScreen extends Screen {

	// bigppgui
	public HudScreen() {
		super(new LiteralText("bigppgui"));
	}

	// this renders the hud module when the screen is opened, it doesn't need any events since its already extending Screen.
	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
		Reference.blur.render(1);

		for(HudModule m : Main.hudManager.hudModules) {
			m.drawDraggable(matrix, mouseX, mouseY);
		}

		super.render(matrix, mouseX, mouseY, delta);
	}
}
