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
        Bedroom.INSTANCE.addModule(new ChatScreen());
        Bedroom.INSTANCE.addModule(new EnabledModules());
        Bedroom.INSTANCE.addModule(new ClickGui());
        Bedroom.INSTANCE.addModule(new HudEditor());
        Bedroom.INSTANCE.addModule(new CommandLine());
        Bedroom.INSTANCE.addModule(new Options());
        Bedroom.INSTANCE.addModule(new DiscordRpc());

        // pvp
        Bedroom.INSTANCE.addModule(new AutoArmor());
        Bedroom.INSTANCE.addModule(new AutoCrystal());
        Bedroom.INSTANCE.addModule(new Criticals());
        Bedroom.INSTANCE.addModule(new FootExp());
        Bedroom.INSTANCE.addModule(new HoleFiller());
        Bedroom.INSTANCE.addModule(new HoleTp());
        Bedroom.INSTANCE.addModule(new HotbarRefill());
        Bedroom.INSTANCE.addModule(new Surround());
        Bedroom.INSTANCE.addModule(new SwingAura());

        // misc
        Bedroom.INSTANCE.addModule(new AutoLog());
        Bedroom.INSTANCE.addModule(new ChatSpammer());
        Bedroom.INSTANCE.addModule(new DeathCoords());
        Bedroom.INSTANCE.addModule(new Nuker());
        Bedroom.INSTANCE.addModule(new PacketCancel());
        Bedroom.INSTANCE.addModule(new SoftJoin());
        Bedroom.INSTANCE.addModule(new Timer());

        // movement
        Bedroom.INSTANCE.addModule(new AutoWalk());
        Bedroom.INSTANCE.addModule(new ElytraFly());
        Bedroom.INSTANCE.addModule(new Fly());
        Bedroom.INSTANCE.addModule(new GuiMove());
        Bedroom.INSTANCE.addModule(new LockOnBlock());
        Bedroom.INSTANCE.addModule(new NoSlow());
        Bedroom.INSTANCE.addModule(new Scaffold());
        Bedroom.INSTANCE.addModule(new Speed());
        Bedroom.INSTANCE.addModule(new Sprint());
        Bedroom.INSTANCE.addModule(new Step());
        Bedroom.INSTANCE.addModule(new Strafe());

        // player
        Bedroom.INSTANCE.addModule(new AntiHunger());
        Bedroom.INSTANCE.addModule(new AutoTotem());
        Bedroom.INSTANCE.addModule(new ElytraReplace());
        Bedroom.INSTANCE.addModule(new FakePlayer());
        Bedroom.INSTANCE.addModule(new FastPlace());
        Bedroom.INSTANCE.addModule(new FreeCam());
        Bedroom.INSTANCE.addModule(new Jesus());
        Bedroom.INSTANCE.addModule(new LiquidPlace());
        Bedroom.INSTANCE.addModule(new NoFall());
        Bedroom.INSTANCE.addModule(new PlayerVelocity());
        Bedroom.INSTANCE.addModule(new Stealer());

        // render
        Bedroom.INSTANCE.addModule(new BlockHighlight());
        Bedroom.INSTANCE.addModule(new CameraZoom());
        Bedroom.INSTANCE.addModule(new EntityEsp());
        Bedroom.INSTANCE.addModule(new FullBright());
        Bedroom.INSTANCE.addModule(new Nametags());
        Bedroom.INSTANCE.addModule(new RainbowEnchant());
        Bedroom.INSTANCE.addModule(new RenderCancel());
        Bedroom.INSTANCE.addModule(new ShulkerPeek());
        Bedroom.INSTANCE.addModule(new StorageEsp());
        Bedroom.INSTANCE.addModule(new Tracers());
        Bedroom.INSTANCE.addModule(new VibrantShader());
        Bedroom.INSTANCE.addModule(new Xray());
        // crystalesp
        // entityesp - player, mobs, items.
        // storage esp
        // voidesp
        // hole esp

        // tracers - players, mobs, items, portals.
    }
}
