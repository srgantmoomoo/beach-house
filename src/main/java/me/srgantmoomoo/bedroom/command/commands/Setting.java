package me.srgantmoomoo.bedroom.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import org.w3c.dom.Text;

public class Setting extends Command {

    public Setting() {
        super("setting", "allows you to change settings of modules.", "setting <module> <setting> <value>", "s");
    }

    TextFormatting GREEN = TextFormatting.GREEN;
    TextFormatting RED = TextFormatting.RED;
    TextFormatting GRAY = TextFormatting.GRAY;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 3) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String moduleName = args[0];
        moduleName = moduleName.replaceAll("\\s", "");
        String settingName = args[1];
        Module module = Main.moduleManager.getModule(moduleName);
        me.srgantmoomoo.bedroom.module.setting.Setting setting = Main.settingManager.getSettingsByMod(module).stream().filter(setting1 -> setting1.name.equalsIgnoreCase(settingName)).findFirst().orElse(null);

        if(module == null) {
            CommandManager.addChatMessage("the module " + RED + moduleName + GRAY + " does not exist dumfuck.");
            return;
        }

        if(setting == null) {
            CommandManager.addChatMessage("the setting " + RED + settingName + GRAY + " does not exist fucking idiot.");
            return;
        }

        String inputValue = args[2];

        setValue(module, setting, inputValue);
    }

    private void setValue(Module module, me.srgantmoomoo.bedroom.module.setting.Setting setting, String value) {
        if(setting instanceof BooleanSetting) {
            if(!value.equalsIgnoreCase("true") || !value.equalsIgnoreCase("false")) {
                CommandManager.addChatMessage("boolean value must be either " + GREEN + "true " + GRAY + "or " + RED + "false" + GRAY + ".");
                return;
            }

            ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(value));
            CommandManager.addChatMessage("" + GREEN + setting + GRAY + " of" + GREEN + module + GRAY + " was set to " + (module.isEnabled() ? GREEN + value : RED + value));
        }
    }
}
