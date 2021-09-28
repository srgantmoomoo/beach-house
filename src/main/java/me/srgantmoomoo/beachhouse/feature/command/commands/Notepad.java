package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
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
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        String initial = args[0];

        if(initial.equals("notes")) {
            if(!Main.notepadManager.notes.isEmpty()) {

                String wuw = TextFormatting.GRAY + "" + TextFormatting.BOLD + "wuw" + TextFormatting.AQUA + " ~";
                Bedroom.commandManager.addCustomChatMessage(wuw);
                String nothing = TextFormatting.AQUA + " ";
                Bedroom.commandManager.addCustomChatMessage(nothing);
                String notesTitle = TextFormatting.WHITE + "notes" + TextFormatting.GRAY + ":";
                Bedroom.commandManager.addCustomChatMessage(notesTitle);

                for (me.srgantmoomoo.beachhouse.feature.notepad.Notepad note : Main.notepadManager.notes) {
                    Bedroom.commandManager.addCustomChatMessage(Formatting.WHITE + note.getName());
                }

                Bedroom.commandManager.addCustomChatMessage(nothing);
                String uwu = TextFormatting.GRAY + "" + TextFormatting.BOLD + "uwu" + TextFormatting.AQUA + " ~";
                Bedroom.commandManager.addCustomChatMessage(uwu);

            }else Bedroom.commandManager.addChatMessage("u have no notes stupid.");
            return;
        }

        if(args.length == 1) {
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
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
        switch (action) {
            case "write":

                if (!Main.notepadManager.isNote(noteName)) {

                    Main.notepadManager.addNote(noteName, noteMessageInput);
                    Bedroom.commandManager.addChatMessage(Formatting.GREEN + "wrote" + Formatting.GRAY + " new note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", to the notepad.");

                } else
                    Bedroom.commandManager.addChatMessage("you cannot write a note" + " (" + Formatting.WHITE + noteName + Formatting.GRAY + ") that" + Formatting.RED + " already exists"
                            + Formatting.GRAY + ".");

                break;
            case "erase":

                if (Main.notepadManager.isNote(noteName)) {

                    Main.notepadManager.removeNote(noteName);
                    Bedroom.commandManager.addChatMessage(Formatting.RED + "erased" + Formatting.GRAY + " note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", from the notepad :(");

                } else
                    Bedroom.commandManager.addChatMessage("you cannot erase a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + " (" + Formatting.WHITE + noteName + Formatting.GRAY
                            + "). silly dumb fucking piece of shit.");

                break;
            case "read":

                if (Main.notepadManager.isNote(noteName)) {

                    me.srgantmoomoo.beachhouse.feature.notepad.Notepad note1 = Main.notepadManager.getNoteByName(noteName);
                    Bedroom.commandManager.addChatMessage(Formatting.WHITE + note1.getName() + Formatting.GRAY + note1.getMessage());

                } else
                    Bedroom.commandManager.addChatMessage("you cannot read a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + " (" + Formatting.WHITE + noteName + Formatting.GRAY
                            + ").");

                break;
            default:
                Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
                break;
        }
    }
}
