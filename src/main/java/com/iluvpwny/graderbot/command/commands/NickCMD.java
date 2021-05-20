package com.iluvpwny.graderbot.command.commands;

import com.iluvpwny.graderbot.command.Command;
import net.dv8tion.jda.api.entities.Message;

public class NickCMD extends Command {
    public NickCMD() {
        super("Nick", "Change bot nickname", "nick <ชื่อ>",true);
    }

    @Override
    public void execute(String[] args, Message message) {
        if(!message.getAuthor().getId().equals("369794236830187523"))return;
        StringBuilder bobTheBuilder = new StringBuilder();
        if(args.length >= 1) {
            for(String str : args){
                bobTheBuilder.append(str).append(" ");
            }
            message.delete().queue();
            message.getGuild().getSelfMember().modifyNickname(bobTheBuilder.toString()).queue();
        }
    }
}
