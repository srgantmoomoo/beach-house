package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import me.srgantmoomoo.bedroom.util.font.JColor;
import net.minecraft.util.Formatting;

public class Setting extends Command {

    public Setting() {
        super("setting", "allows you to change settings of modules.", "setting <module> <setting> <value>", "set");
    }

    Formatting GREEN = Formatting.GREEN;
    Formatting RED = Formatting.RED;
    Formatting GRAY = Formatting.GRAY;
    Formatting WHITE = Formatting.WHITE;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 3) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String moduleInput = args[0];
        String settingNameInput = args[1];
        String settingValueInput = args[2];
        Module module = Bedroom.INSTANCE.moduleManager.getModuleByID(moduleInput);
        me.srgantmoomoo.bedroom.module.setting.Setting setting = Bedroom.INSTANCE.settingManager.getSettingByName(module, settingNameInput);

        if(module == null) {
            Bedroom.INSTANCE.commandManager.addChatMessage("the module " + RED + moduleInput + GRAY + " does not exist dumfuck.");
            return;
        }

        if(setting == null) {
            Bedroom.INSTANCE.commandManager.addChatMessage("the setting " + RED + settingNameInput + GRAY + " does not exist for the module " + WHITE + moduleInput + GRAY + ".");
            return;
        }

        if(setting instanceof BooleanSetting) {
            if(settingValueInput.equalsIgnoreCase("true") || settingValueInput.equalsIgnoreCase("false")) {
                ((BooleanSetting) setting).setEnabled(Boolean.parseBoolean(settingValueInput));
                Bedroom.INSTANCE.commandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + (settingValueInput.equalsIgnoreCase("true") ? GREEN + settingValueInput + GRAY + "." : RED + settingValueInput + GRAY + "."));
            }else Bedroom.INSTANCE.commandManager.addChatMessage("boolean value must be either " + GREEN + "true " + GRAY + "or " + RED + "false" + GRAY + ".");
        }

        if(setting instanceof NumberSetting) {
            try {
                double val = Double.parseDouble(settingValueInput);

                if(val > ((NumberSetting) setting).getMaximum()) val = ((NumberSetting) setting).getMaximum();
                else if(val < ((NumberSetting) setting).getMinimum()) val = ((NumberSetting) setting).getMinimum();

                ((NumberSetting) setting).setValue(val);
                Bedroom.INSTANCE.commandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + GREEN + val + GRAY + ".");
            } catch (NumberFormatException invalid) {
                Bedroom.INSTANCE.commandManager.addChatMessage("number value " + RED + settingValueInput + GRAY + " is invalid.");
            }
        }

        if(setting instanceof ModeSetting) {
            if(((ModeSetting) setting).modes.contains(settingValueInput)) {
                ((ModeSetting) setting).setMode(settingValueInput);
                Bedroom.INSTANCE.commandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was set to " + GREEN + settingValueInput + GRAY + ".");
            }else Bedroom.INSTANCE.commandManager.addChatMessage("the mode " + RED + settingValueInput + GRAY + " does not exist for the module " + WHITE + module.name + GRAY + ". sorry :'(");
        }

        if(setting instanceof ColorSetting) {
            try {
                int valR = Integer.parseInt(settingValueInput.substring(0, 3));
                int valG = Integer.parseInt(settingValueInput.substring(3, 6));
                int valB = Integer.parseInt(settingValueInput.substring(6, 9));
                int valA = Integer.parseInt(settingValueInput.substring(9, 12));

                ((ColorSetting) setting).setValue(false, new JColor(valR, valG, valB, valA));
                Bedroom.INSTANCE.commandManager.addChatMessage("" + WHITE + setting.name + GRAY + " of " + WHITE + module.name + GRAY + " was colorizeddddd woo.");
            } catch (Exception invalid) {
                Bedroom.INSTANCE.commandManager.addChatMessage("color value " + RED + settingValueInput + GRAY + " is invalid. colors are processed in RRRGGGBBBAAA format.");
            }
        }
    }
}