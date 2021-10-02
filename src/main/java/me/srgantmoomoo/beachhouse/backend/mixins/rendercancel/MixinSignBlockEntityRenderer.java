package me.srgantmoomoo.beachhouse.backend.mixins.rendercancel;

import me.srgantmoomoo.beachhouse.feature.module.modules.render.RenderCancel;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(SignBlockEntityRenderer.class)
public class MixinSignBlockEntityRenderer {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/SignBlockEntity;updateSign(ZLjava/util/function/Function;)[Lnet/minecraft/text/OrderedText;"))
    private OrderedText[] updateSignProxy(SignBlockEntity sign, boolean filterText, Function<Text, OrderedText> textOrderingFunction) {
        if(Bedroom.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.signText.isEnabled())
            return null;
        return sign.updateSign(filterText, textOrderingFunction);
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 4))
    private int loopTextLengthProxy(int i) {
        if(Bedroom.moduleManager.isModuleEnabled("render cancel") && RenderCancel.INSTANCE.signText.isEnabled())
            return 0;
        return i;
    }
}
