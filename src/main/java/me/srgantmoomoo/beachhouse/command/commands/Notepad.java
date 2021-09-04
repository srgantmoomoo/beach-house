package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.util.Formatting;

//TODO improve on this
public class Notepad extends Command {

    public Notepad() {
        super("notepad", "does notee padee thingees.", "notepad <name> write <notes> | notepad <name> erase | notepad <name> read | notepad notes", "n");
    }

    private String noteMessageInput;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length == 0) {
            CommandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        String initial = args[0];

        if(initial.equals("notes")) {
            if(!Main.notepadManager.notes.isEmpty()) {

                String nothing = TextFormatting.AQUA + " ";
                String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
                CommandManager.addCustomChatMessage(wuw);
                CommandManager.addCustomChatMessage(nothing);

                for (me.srgantmoomoo.beachhouse.notepad.Notepad note : Main.notepadManager.notes) {
                    CommandManager.addCustomChatMessage(Formatting.WHITE + note.getName());
                }

                CommandManager.addCustomChatMessage(nothing);
                String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
                CommandManager.addCustomChatMessage(uwu);

            }else CommandManager.addChatMessage("u have no notes stupid.");
            return;
        }

        // defines the message when a note is written.
        if(args.length >= 3) {
            StringBuilder msg = new StringBuilder();
            boolean flag = true;
            for (String string : args) {
                if (flag) {
                    flag = false;
                    continue;
                }
                msg.append(string).append(" ");
            }

            noteMessageInput = msg.toString().replace("write", "");
        }

        String action = args[1];
        String noteName = initial;
        if(action.equals("write")) {

            if(!Main.notepadManager.isNote(noteName)) {

                Main.notepadManager.addNote(noteName, noteMessageInput);
                CommandManager.addChatMessage(Formatting.GREEN + "wrote" + Formatting.GRAY + " new note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", to the notepad.");

            }else CommandManager.addChatMessage("you cannot write a note" + " (" + Formatting.WHITE + noteName + Formatting.GRAY + ") that" + Formatting.RED + " already exists"
                    + Formatting.GRAY + ".");

        }else if(action.equals("erase")) {

            if(Main.notepadManager.isNote(noteName)) {

                Main.notepadManager.removeNote(noteName);
                CommandManager.addChatMessage(Formatting.RED + "erased" + Formatting.GRAY + " note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", from the notepad :(");

            }else CommandManager.addChatMessage("you cannot erase a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + "(" + Formatting.WHITE + noteName + Formatting.GRAY
                    + "). silly dumb fucking piece of shit.");

        }else if(action.equals("read")) {

            if(Main.notepadManager.isNote(noteName)) {

                me.srgantmoomoo.beachhouse.notepad.Notepad note1 = Main.notepadManager.getNoteByName(noteName);
                CommandManager.addChatMessage(Formatting.WHITE + note1.getName() + Formatting.GRAY + note1.getMessage());

            }else CommandManager.addChatMessage("you cannot read a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + "(" + Formatting.WHITE + noteName + Formatting.GRAY
                    + "). silly dumb fucking piece of shit.");

        }else
            CommandManager.correctUsageMsg(getName(), getSyntax());
    }
}
