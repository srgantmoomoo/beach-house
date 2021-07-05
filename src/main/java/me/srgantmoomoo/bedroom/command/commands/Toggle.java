package me.srgantmoomoo.bedroom.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.ModuleManager;

public class Toggle extends Command {
	
	public Toggle() {
		super("toggle", "toggles a module by name.", "toggle <module>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length != 1) {
			CommandManager.correctUsageMsg(name, syntax);
			return;
		}

		String moduleName = args[0];
		boolean moduleFound = false;
		for(Module module : Main.moduleManager.getModules()) {
			String moduleIn = module.name;
			moduleIn = moduleIn.replaceAll("\\s", "");
			if(moduleIn.equalsIgnoreCase(moduleName)) {
				module.toggle();
				CommandManager.addChatMessage(module.name + " " + (module.isEnabled() ? TextFormatting.GREEN + "enabled" + TextFormatting.GRAY + "." : TextFormatting.DARK_RED + "disabled" + TextFormatting.GRAY + "."));
				moduleFound = true;
				break;
			}
		}
		if(!moduleFound) {
			CommandManager.addChatMessage("the module, " + TextFormatting.RED + moduleName + TextFormatting.GRAY + ", was not found.");
		}
	}
}