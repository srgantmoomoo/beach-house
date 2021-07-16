package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class ModuleList extends Command {

    public ModuleList() {
        super("modulelist", "gets a list of all the modules.", "moduleList", "ml");
    }
    public static Bedroom bedroom;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }
       // ModuleManager newnow = new ModuleManager();
        //ArrayList<Module> n = newnow.getModules();

        String nothing = TextFormatting.AQUA + " ";
        String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(wuw));
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(nothing));

        for(Module module : Bedroom.moduleManager.getModules()) {
            CommandManager.addChatMessage(TextFormatting.WHITE + module.getCategory().name + ": " + TextFormatting.GRAY + module.getName() +
                    (module.isEnabled() ? TextFormatting.GREEN + " enabled" : TextFormatting.RED + " disabled"));
        }

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(nothing));
        String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(uwu));

        //n.sort(Comparator.comparing(Module::getCategory));
    }
}