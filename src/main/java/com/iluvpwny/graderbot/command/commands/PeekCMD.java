package com.iluvpwny.graderbot.command.commands;

import com.iluvpwny.graderbot.command.Command;
import com.iluvpwny.graderbot.utils.Pair;
import com.iluvpwny.graderbot.utils.Score;
import com.iluvpwny.graderbot.utils.Scraper;
import com.iluvpwny.graderbot.utils.UserData;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

import java.util.concurrent.TimeUnit;

public class PeekCMD extends Command {
    public PeekCMD() {
        super("peek", "peeking :)", "peek <id>", true);
    }

    @Override
    public void execute(String[] args, Message message) {
        if(!message.getAuthor().getId().equals("369794236830187523"))return;
        Pair<String,String> user = UserData.getUser(args[0]);
        if(user == null){
            message.getChannel().sendMessage("กรุณา login ก่อนใช้งาน").queue(mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS));
            if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queueAfter(8,TimeUnit.SECONDS);
            return;
        }
        Score score = Scraper.getData(user.getKey(), user.getValue());
        if(score.getFullscore() == -200){
            message.getChannel().sendMessage("ชื่อ หรือ รหัสผ่าน ไม่ถูกต้อง").queue(mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS));
            if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queueAfter(8,TimeUnit.SECONDS);
            return;
        }
        message.getChannel().sendMessage("<@" + message.getAuthor().getId() + ">" +
                "\nคุณได้คะแนน : " + score.getScore() +
                " คะแนน\nทำได้เต็ม : " + score.getFull() +
                " ข้อ\nจากคะแนนเต็ม : " + score.getFullscore() +
                " คะแนน\nคิดเป็น : " + score.getPercent() + "%" +
                " \nมีโจทย์ทั้งหมด : " + score.getQuestions() + " ข้อ").queue(mess -> mess.addReaction("❌").queue());

        if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queue();
        else System.out.println(message.getAuthor().getName());
    }
}
