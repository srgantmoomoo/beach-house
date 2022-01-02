package me.srgantmoomoo.beachhouse.feature.module;

import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.combat.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.miscellaneous.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.movement.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.player.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.render.*;
import me.srgantmoomoo.bedroom.Bedroom;

public class Modules {
    public static void init() {
        // beach house
        Bedroom.addModule(new ChatScreen());
        Bedroom.addModule(new EnabledModules());
        Bedroom.addModule(new ClickGui());
        Bedroom.addModule(new HudEditor());
        Bedroom.addModule(new CommandLine());
        Bedroom.addModule(new Options());
        Bedroom.addModule(new DiscordRpc());

        // pvp
        Bedroom.addModule(new AutoArmor());
        Bedroom.addModule(new AutoCrystal());
        Bedroom.addModule(new Criticals());
        Bedroom.addModule(new FootExp());
        Bedroom.addModule(new HoleFiller());
        Bedroom.addModule(new HoleTp());
        Bedroom.addModule(new HotbarRefill());
        Bedroom.addModule(new Surround());
        Bedroom.addModule(new SwingAura());

        // misc
        Bedroom.addModule(new AutoLog());
        Bedroom.addModule(new ChatSpammer());
        Bedroom.addModule(new DeathCoords());
        Bedroom.addModule(new Nuker());
        Bedroom.addModule(new PacketCancel());
        Bedroom.addModule(new SoftJoin());
        Bedroom.addModule(new Timer());

        // movement
        Bedroom.addModule(new AutoWalk());
        Bedroom.addModule(new ElytraFly());
        Bedroom.addModule(new Fly());
        Bedroom.addModule(new GuiMove());
        Bedroom.addModule(new LockOnBlock());
        Bedroom.addModule(new NoSlow());
        Bedroom.addModule(new Scaffold());
        Bedroom.addModule(new Speed());
        Bedroom.addModule(new Sprint());
        Bedroom.addModule(new Step());
        Bedroom.addModule(new Strafe());

        // player
        Bedroom.addModule(new AntiHunger());
        Bedroom.addModule(new AutoTotem());
        Bedroom.addModule(new ElytraReplace());
        Bedroom.addModule(new FakePlayer());
        Bedroom.addModule(new FastPlace());
        Bedroom.addModule(new FreeCam());
        Bedroom.addModule(new Jesus());
        Bedroom.addModule(new LiquidPlace());
        Bedroom.addModule(new NoFall());
        Bedroom.addModule(new PlayerVelocity());
        Bedroom.addModule(new Stealer());

        // render
        Bedroom.addModule(new BlockHighlight());
        Bedroom.addModule(new CameraZoom());
        Bedroom.addModule(new EntityEsp());
        Bedroom.addModule(new FullBright());
        Bedroom.addModule(new Nametags());
        Bedroom.addModule(new RainbowEnchant());
        Bedroom.addModule(new RenderCancel());
        Bedroom.addModule(new ShulkerPeek());
        Bedroom.addModule(new StorageEsp());
        Bedroom.addModule(new Tracers());
        Bedroom.addModule(new VibrantShader());
        Bedroom.addModule(new Xray());
        // crystalesp
        // entityesp - player, mobs, items.
        // storage esp
        // voidesp
        // hole esp

        // tracers - players, mobs, items, portals.
    }
}
