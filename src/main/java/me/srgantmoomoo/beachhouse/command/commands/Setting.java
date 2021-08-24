package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.util.Formatting;

public class Setting extends Command {

    public Setting() {
        super("setting", "allows you to change settings of modules.", "setting <module> <setting> <value>", "s");
    }

    Formatting GREEN = Formatting.GREEN;
    Formatting RED = Formatting.RED;
    Formatting GRAY = Formatting.GRAY;
    Formatting WHITE = Formatting.WHITE;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 3) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String moduleInput = args[0];
        String settingNameInput = args[1];
        String settingValueInput = args[2];
        Module module = Bedroom.moduleManager.getModuleByID(moduleInput);
        me.srgantmoomoo.bedroom.module.setting.Setting setting = Bedroom.settingManager.getSettingByName(module, settingNameInput);

        if(module == null) {
            CommandManager.addChatMessage("the module " + RED + moduleInput + GRAY + " does not exist dumfuck.");
            return;
        }

        if(setting == null) {
            CommandManager.addChatMessage("the setting " + RED + settingNameInput + GRAY + " does not exist for the module " + WHITE + moduleInput + GRAY + ".");
            return;
        }

        if(setting instanceof BooleanSetting) {
            if(settingValueInput.equalsIgnoreCase("true") || settingValueInput.equalsIgnoreCase("false")) {
                ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(settingValueInput));
                CommandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + (settingValueInput.equalsIgnoreCase("true") ? GREEN + settingValueInput + GRAY + "." : RED + settingValueInput + GRAY + "."));
            }else CommandManager.addChatMessage("boolean value must be either " + GREEN + "true " + GRAY + "or " + RED + "false" + GRAY + ".");
        }

        if(setting instanceof NumberSetting) {
            try {
                double val = Double.parseDouble(settingValueInput);

                if(val > ((NumberSetting) setting).getMaximum()) val = ((NumberSetting) setting).getMaximum();
                else if(val < ((NumberSetting) setting).getMinimum()) val = ((NumberSetting) setting).getMinimum();

                ((NumberSetting) setting).setValue(val);
                CommandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + GREEN + val + GRAY + ".");
            } catch (NumberFormatException invalid) {
                CommandManager.addChatMessage("number value " + RED + settingValueInput + GRAY + " is invalid.");
            }
        }

        if(setting instanceof ModeSetting) {
            if(((ModeSetting) setting).modes.contains(settingValueInput)) {
                ((ModeSetting) setting).setMode(settingValueInput);
                CommandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + GREEN + settingValueInput + GRAY + ".");
            }else CommandManager.addChatMessage("the mode " + RED + settingValueInput + GRAY + " does not exist for the module " + WHITE + module.name + GRAY + ". sorry :'(");
        }
    }
}