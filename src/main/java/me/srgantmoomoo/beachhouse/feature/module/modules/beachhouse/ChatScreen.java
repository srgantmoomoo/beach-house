package me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;

public class ChatScreen extends Module {
    public static ChatScreen INSTANCE;

    public BooleanSetting background = new BooleanSetting("background", this, false);

    public ChatScreen() {
        super("chat screen", "chatscreen", "does chatty stuff stupid shit like duh ibbviously.", 0, Category.BEACHHOUSE);
        this.addSettings(background);
        INSTANCE = this;
    }

}
