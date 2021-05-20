package com.iluvpwny.graderbot.command;

import com.google.gson.Gson;
import net.dv8tion.jda.api.entities.Message;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public abstract class Command {

    private final String name;
    private final String desc;
    private final String format;
    private final boolean hidden;
    private static String prefix;

    public Command(String name, String desc,String format,boolean hidden) {
        this.name = name;
        this.desc = desc;
        this.format = format;
        this.hidden = hidden;
    }

    public Command(String name, String desc,String format) {
        hidden = false;
        this.name = name;
        this.desc = desc;
        this.format = format;
    }

    public abstract void execute(String[] args, Message message);

    public static String getPrefix() {
        if(prefix == null){
            try {
                Gson gson = new Gson();
                Reader reader = Files.newBufferedReader(Paths.get("Setting.json"));
                Map<?, ?> map = gson.fromJson(reader, Map.class);
                prefix = (String) map.get("Prefix");
            }catch (IOException io){
                System.err.println("Error loading setting");
            }
        }
        return prefix;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getFormat() {
        return format;
    }

    public boolean isHidden() {
        return hidden;
    }
}
