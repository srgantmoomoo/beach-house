package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.gui.chat.IChatScreen;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChatScreen.class)
public class MixinChatScreen implements IChatScreen {
    @Shadow protected TextFieldWidget chatField;

    @Override
    public String getText() {
        return this.chatField.getText();
    }

    @Override
    public void setText(String text) {
        this.chatField.setText(text);
    }

    @Override
    public TextFieldWidget getWidget() {
        return this.chatField;
    }
}