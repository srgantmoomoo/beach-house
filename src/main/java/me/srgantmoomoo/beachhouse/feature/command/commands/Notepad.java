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
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        String initial = args[0];

        if(initial.equals("notes")) {
            if(!Main.INSTANCE.notepadManager.notes.isEmpty()) {

                String wuw = Formatting.GRAY + "" + Formatting.BOLD + "wuw" + Formatting.AQUA + " ~";
                Bedroom.INSTANCE.commandManager.addCustomChatMessage(wuw);
                String nothing = Formatting.AQUA + " ";
                Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
                String notesTitle = Formatting.WHITE + "notes" + Formatting.GRAY + ":";
                Bedroom.INSTANCE.commandManager.addCustomChatMessage(notesTitle);

                for (me.srgantmoomoo.beachhouse.feature.notepad.Notepad note : Main.INSTANCE.notepadManager.notes) {
                    Bedroom.INSTANCE.commandManager.addCustomChatMessage(Formatting.WHITE + note.getName());
                }

                Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
                String uwu = Formatting.GRAY + "" + Formatting.BOLD + "uwu" + Formatting.AQUA + " ~";
                Bedroom.INSTANCE.commandManager.addCustomChatMessage(uwu);

            }else Bedroom.INSTANCE.commandManager.addChatMessage("u have no notes stupid.");
            return;
        }

        if(args.length == 1) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
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

                if (!Main.INSTANCE.notepadManager.isNote(noteName)) {

                    Main.INSTANCE.notepadManager.addNote(noteName, noteMessageInput);
                    Bedroom.INSTANCE.commandManager.addChatMessage(Formatting.GREEN + "wrote" + Formatting.GRAY + " new note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", to the notepad.");

                } else
                    Bedroom.INSTANCE.commandManager.addChatMessage("you cannot write a note" + " (" + Formatting.WHITE + noteName + Formatting.GRAY + ") that" + Formatting.RED + " already exists"
                            + Formatting.GRAY + ".");

                break;
            case "erase":

                if (Main.INSTANCE.notepadManager.isNote(noteName)) {

                    Main.INSTANCE.notepadManager.removeNote(noteName);
                    Bedroom.INSTANCE.commandManager.addChatMessage(Formatting.RED + "erased" + Formatting.GRAY + " note, " + Formatting.WHITE + noteName + Formatting.GRAY + ", from the notepad :(");

                } else
                    Bedroom.INSTANCE.commandManager.addChatMessage("you cannot erase a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + " (" + Formatting.WHITE + noteName + Formatting.GRAY
                            + "). silly dumb fucking piece of shit.");

                break;
            case "read":

                if (Main.INSTANCE.notepadManager.isNote(noteName)) {

                    me.srgantmoomoo.beachhouse.feature.notepad.Notepad note1 = Main.INSTANCE.notepadManager.getNoteByName(noteName);
                    Bedroom.INSTANCE.commandManager.addChatMessage(Formatting.WHITE + note1.getName() + Formatting.GRAY + note1.getMessage());

                } else
                    Bedroom.INSTANCE.commandManager.addChatMessage("you cannot read a note that" + Formatting.RED + " does not exist" + Formatting.GRAY + " (" + Formatting.WHITE + noteName + Formatting.GRAY
                            + ").");

                break;
            default:
                Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
                break;
        }
    }
}
