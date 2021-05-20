package com.iluvpwny.graderbot.command.commands;

import com.iluvpwny.graderbot.command.Command;
import com.iluvpwny.graderbot.utils.Score;
import com.iluvpwny.graderbot.utils.Scraper;
import com.iluvpwny.graderbot.utils.UserData;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

import java.util.concurrent.TimeUnit;

public class LoginCMD extends Command {
    public LoginCMD() {
        super("Login", "ใช้ในแชตส่วนตัวกับ bot เท่านั้น ไม่ต้องใส่ <>", "login <ชื่อในเว็บ> <รหัส>");
    }

    @Override
    public void execute(String[] args, Message message) {
        if(message.getChannel().getType() != ChannelType.PRIVATE){
            message.delete().queue();
            message.getChannel().sendMessage("กรุณาใช้คำสั่งนี้ในห้องแชตส่วนตัวกับบอทเท่านั้น(click ขวาที่บอทแล้วกด message) เพื่อความปลอดภัย").queue(
                    mess -> mess.delete().queueAfter(8, TimeUnit.SECONDS)
            );
            return;
        }
        if(args.length < 2){
            message.getChannel().sendMessage("กรุณากรอก ชื่อ และ รหัสผ่าน ด้วยคำสั่ง " + getPrefix() + "Login <ชื่อ> <รหัสผ่าน>").queue();
            return;
        }
        Score score = Scraper.getData(args[0],args[1]);
        if(score.getFullscore() == -200){
            message.getChannel().sendMessage("ชื่อ หรือ รหัสผ่าน ไม่ถูกต้อง").queue();
            return;
        }

        UserData.addUser(message.getAuthor().getId(),args[0],args[1]);
        message.getChannel().sendMessage("บันทึกข้อมูลผู้ใช้แล้ว เพื่อนๆ สามารถใช้คำสั้ง " + getPrefix() +"score ได้เลย").queue();
    }
}
