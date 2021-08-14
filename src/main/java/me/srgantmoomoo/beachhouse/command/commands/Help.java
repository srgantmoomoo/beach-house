package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.text.LiteralText;

//TODO optimize code
public class Help extends Command {

    public Help() {
        super("help", "helps u penis.", "help", "h");
    }

    TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    TextFormatting GRAY = TextFormatting.GRAY;
    TextFormatting AQUA = TextFormatting.AQUA;
    TextFormatting BOLD = TextFormatting.BOLD;
    TextFormatting ITALIC = TextFormatting.ITALIC;

    public static Prefix prefix;
    public static Toggle toggle;
    public static Setting setting;
    public static ModuleList moduleList;
    public static SettingList settingList;
    public static Clear clear;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        // MAKE A NEW OBJECT BEFORE TRYING HELP COMMAND STUPID FUCK.
        prefix = new Prefix();
        toggle = new Toggle();
        setting = new Setting();
        moduleList = new ModuleList();
        settingList = new SettingList();
        clear = new Clear();

        welcomeMessage();
        helpMessage(prefix.getName(), prefix.getDescription(), prefix.getSyntax());
        helpMessage(toggle.getName(), toggle.getDescription(), toggle.getSyntax());
        helpMessage(moduleList.getName(), moduleList.getDescription(), moduleList.getSyntax());
        helpMessage(setting.getName(), setting.getDescription(), setting.getSyntax());
        helpMessage(settingList.getName(), settingList.getDescription(), settingList.getSyntax());
        helpMessage(clear.getName(), clear.getDescription(), clear.getSyntax());
        goodbyeMessage();
    }

    private void helpMessage(String commandName, String commandDesc, String commandSyntax) {
        String starter = LIGHT_PURPLE + commandName + GRAY + " - " + commandDesc;
        String syntaxMessage = " [" + CommandManager.prefix + commandSyntax + "]";

        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(starter));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(syntaxMessage));
    }

    private void welcomeMessage() {
        String welcomeString = GRAY + "" + BOLD + Main.name + " " + Main.version + "!";
        String nothing = " ";
        String atClientName = AQUA + "@" + ITALIC + Main.name;

        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(welcomeString));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(nothing));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(atClientName));

    }

    private void goodbyeMessage() {
        String uwu = GRAY + "" + BOLD + "uwu" + AQUA + " ~";
        String nothing = " ";

        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(nothing));
        Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(uwu));
    }

}