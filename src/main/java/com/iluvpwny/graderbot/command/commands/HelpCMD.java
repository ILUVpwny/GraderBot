package com.iluvpwny.graderbot.command.commands;

import com.iluvpwny.graderbot.command.Command;
import com.iluvpwny.graderbot.command.CommandManager;
import net.dv8tion.jda.api.entities.Message;

public class HelpCMD extends Command {
    public HelpCMD() {
        super("help", "เปิดหน้านี้", "help");
    }

    @Override
    public void execute(String[] args, Message message) {
        StringBuilder bobTheBuilder = new StringBuilder();
        for (Command command : CommandManager.getCommands()) {
            if(command.isHidden())continue;
            bobTheBuilder.append("`")
                    .append(getPrefix())
                    .append(command.getFormat())
                    .append("`\n")
                    .append("^^  ")
                    .append(command.getDesc())
                    .append("\n");
        }

        message.getChannel().sendMessage("--คำสั่ง--\n" + bobTheBuilder.toString() + "\nเจอ bug บอก ILUVpwny").queue();
    }
}
//`?"+command.getFormat()+"`"