package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.notepad.NotepadManager;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;

public class Notepad extends Command {

    public Notepad() {
        super("notepad", "does notee padee thingees.", "notepad <noteName> write <notes> | notepad <noteName> erase | notepad notes", "n");
    }
    private static String noteMessageInput;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length == 0) {
            CommandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        String initial = args[0];

        if(initial.equals("notes")) {
            if(!Main.notepadManager.notes.isEmpty()) {
                for (me.srgantmoomoo.beachhouse.notepad.Notepad note : Main.notepadManager.notes) {
                    CommandManager.addChatMessage(note.getName() + note.getMessage());
                }
            }else CommandManager.addChatMessage("u have no notes stupid.");
            return;
        }

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
            CommandManager.addChatMessage("added " + noteMessageInput);
            //me.srgantmoomoo.postman.client.module.modules.pvp.AutoCope.setMessage(args[0] + " " + msg.toString());
            //ModuleManager.addChatMessage("set autoCope message to " + ChatFormatting.GREEN + args[0] + " " + msg.toString() + ChatFormatting.GRAY + ".");
        }

        String action = args[1];
        String noteName = initial;

        if(action.equals("write")) {
            Main.notepadManager.addNote(noteName, noteMessageInput);
        }else if(action.equals("erase")) {
            Main.notepadManager.removeNote(noteName);
        }else
            CommandManager.correctUsageMsg(getName(), getSyntax());
    }
}
