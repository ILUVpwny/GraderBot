package com.iluvpwny.graderbot.command.commands;

import com.iluvpwny.graderbot.command.Command;
import com.iluvpwny.graderbot.utils.Pair;
import com.iluvpwny.graderbot.utils.Score;
import com.iluvpwny.graderbot.utils.Scraper;
import com.iluvpwny.graderbot.utils.UserData;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

import java.util.concurrent.TimeUnit;

public class RandomCMD extends Command {

    public RandomCMD() {
        super("random", "สุ่มข้อที่ไม่ได้ทำ", "random");
    }

    @Override
    public void execute(String[] args, Message message) {
        Pair<String,String> user = UserData.getUser(message.getAuthor().getId());
        if(user == null){
            message.getChannel().sendMessage("กรุณา login ก่อนใช้งาน").queue(mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS));
            if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queueAfter(8,TimeUnit.SECONDS);
            return;
        }
        String random;
        if(args.length >= 1) {
            try {
                int maxScore = Integer.parseInt(args[0]);
                random = Scraper.getRandom(user.getKey(), user.getValue(),maxScore);
            }catch (NumberFormatException e){
                message.getChannel().sendMessage("ตัวเองไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง").queue(mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS));
                if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queueAfter(8,TimeUnit.SECONDS);
                return;
            }
        }else random = Scraper.getRandom(user.getKey(), user.getValue(),0);
        if(random == null){
            message.getChannel().sendMessage("เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง").queue(mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS));
            if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queueAfter(8,TimeUnit.SECONDS);
            return;
        }

        message.getChannel().sendMessage("<@" + message.getAuthor().getId() + ">"
                +"\nสุ่มได้ข้อ " + random + " ไปทำเลยครับ ไม่ยากหรอก").queue(mess -> mess.addReaction("❌").queue());

        if(message.getChannel().getType() != ChannelType.PRIVATE)message.delete().queue();
        else System.out.println(message.getAuthor().getName());    }
}
