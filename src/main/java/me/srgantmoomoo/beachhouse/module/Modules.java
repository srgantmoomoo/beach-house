package me.srgantmoomoo.beachhouse.module;

import me.srgantmoomoo.beachhouse.module.modules.beachhouse.*;
import me.srgantmoomoo.beachhouse.module.modules.combat.*;
import me.srgantmoomoo.beachhouse.module.modules.miscellaneous.*;
import me.srgantmoomoo.beachhouse.module.modules.movement.*;
import me.srgantmoomoo.beachhouse.module.modules.player.*;
import me.srgantmoomoo.beachhouse.module.modules.render.*;
import me.srgantmoomoo.bedroom.Bedroom;

public class Modules {
    public static void init() {
        Bedroom.addModule(new ModuleList());
        Bedroom.addModule(new HudEditor());

        Bedroom.addModule(new AutoCrystal());
        Bedroom.addModule(new Criticals());
        Bedroom.addModule(new SwingAura());

        Bedroom.addModule(new AntiNick());

        Bedroom.addModule(new Speed());
        Bedroom.addModule(new Sprint());
        Bedroom.addModule(new Strafe());

        Bedroom.addModule(new Jesus());
        Bedroom.addModule(new NoFall());

        Bedroom.addModule(new Esp());
        Bedroom.addModule(new FullBright());
        Bedroom.addModule(new RainbowEnchant());
        Bedroom.addModule(new Xray());
        // crystalesp
        // entityesp - player, mobs, items.
        // storage esp
        // voidesp
        // hole esp

        // tracers - players, mobs, items, portals.
    }
}
