package me.srgantmoomoo.beachhouse.module;

import me.srgantmoomoo.beachhouse.module.modules.beachhouse.ModuleList;
import me.srgantmoomoo.beachhouse.module.modules.beachhouse.PlayerInfo;
import me.srgantmoomoo.beachhouse.module.modules.beachhouse.TabGUI;
import me.srgantmoomoo.beachhouse.module.modules.combat.AutoCrystal;
import me.srgantmoomoo.beachhouse.module.modules.combat.Criticals;
import me.srgantmoomoo.beachhouse.module.modules.combat.SwingAura;
import me.srgantmoomoo.beachhouse.module.modules.miscellaneous.AntiNick;
import me.srgantmoomoo.beachhouse.module.modules.movement.Speed;
import me.srgantmoomoo.beachhouse.module.modules.movement.Sprint;
import me.srgantmoomoo.beachhouse.module.modules.movement.Strafe;
import me.srgantmoomoo.beachhouse.module.modules.player.Jesus;
import me.srgantmoomoo.beachhouse.module.modules.render.ESP;
import me.srgantmoomoo.beachhouse.module.modules.render.FullBright;
import me.srgantmoomoo.beachhouse.module.modules.render.Xray;
import me.srgantmoomoo.bedroom.Bedroom;

public class Modules {
    public static void init() {
        Bedroom.addModule(new ModuleList());
        Bedroom.addModule(new PlayerInfo());
        Bedroom.addModule(new TabGUI());

        Bedroom.addModule(new AutoCrystal());
        Bedroom.addModule(new Criticals());
        Bedroom.addModule(new SwingAura());

        Bedroom.addModule(new AntiNick());

        Bedroom.addModule(new Speed());
        Bedroom.addModule(new Sprint());
        Bedroom.addModule(new Strafe());

        Bedroom.addModule(new Jesus());

        Bedroom.addModule(new ESP());
        Bedroom.addModule(new FullBright());
        Bedroom.addModule(new Xray());
        // crystalesp
        // entityesp - player, mobs, items.
        // storage esp
        // voidesp
        // hole esp

        // tracers - players, mobs, items, portals.
    }
}
