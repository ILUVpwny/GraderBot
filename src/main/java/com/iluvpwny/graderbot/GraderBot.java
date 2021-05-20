package com.iluvpwny.graderbot;

import com.google.gson.Gson;
import com.iluvpwny.graderbot.command.CommandManager;
import com.iluvpwny.graderbot.eventhandler.ChatHandler;
import com.iluvpwny.graderbot.eventhandler.ReactionHandler;
import com.iluvpwny.graderbot.utils.UserData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class GraderBot {
    public static void main(String[] args) throws InterruptedException, LoginException {
        String token = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("Setting.json"));
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            token = (String) map.get("Token");
        }catch (IOException io){
            System.err.println("Error loading setting");
        }

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new ChatHandler())
                .addEventListeners(new ReactionHandler())
                .build();

        jda.awaitReady();
        UserData.loadUser();
        CommandManager.init();
    }
}