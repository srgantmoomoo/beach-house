package me.srgantmoomoo.beachhouse.module.modules.render;

import me.srgantmoomoo.bedroom.module.Module;

public class RainbowEnchant extends Module {

    public RainbowEnchant() {
        super("rainbow enchant", "rainbowenchant", "does rainbowey stuffey.", 0, Category.RENDER);
    }

    /*public void onEvent(Event e) {
        if(e instanceof EventGetGlintShaders) {
            ((EventGetGlintShaders) e).setShader(Shaders.getRainbowEnchantShader());
            ((EventGetGlintShaders) e).setCancelled(true);
        }
    }*/

}
