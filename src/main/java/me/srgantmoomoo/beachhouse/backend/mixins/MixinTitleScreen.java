package me.srgantmoomoo.beachhouse.backend.mixins;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "render")
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta, CallbackInfo info) {
        int width = Reference.minecraft.getWindow().getScaledWidth();
        int height = Reference.minecraft.getWindow().getScaledHeight();

        drawStringWithShadow(matrix, this.textRenderer, "beach house is loaded!", width - 120, height - 20, 0xfff868fB);
    }
}