package me.srgantmoomoo.beachhouse.ui.clickgui;

import net.minecraft.client.MinecraftClient;

public class Component {
	
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public void renderComponent() {}

    public void updateComponent(int mouseX, int mouseY) {}

    public void mouseClicked(int mouseX, int mouseY, int button) {}

    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {}

    public void keyTyped(char typedChar, int key) {}

    public void closeAllSub() {}

    public void setOff(final int newOff) {}

    public int getHeight() {
        return 0;
    }

}
