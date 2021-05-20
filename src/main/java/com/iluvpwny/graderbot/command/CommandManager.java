package com.iluvpwny.graderbot.command;

import com.iluvpwny.graderbot.GraderBot;
import org.reflections.Reflections;

import java.util.*;

public class CommandManager {

    private static final List<Command> COMMANDS = new ArrayList<>();

    public static void init(){
        Reflections reflections = new Reflections(GraderBot.class.getPackage().getName());
        Set<Class<?extends Command>> commandClass = reflections.getSubTypesOf(Command.class);

        commandClass.forEach(clazz -> {
            try {
                Command cmd = clazz.newInstance();
                COMMANDS.add(cmd);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static Collection<Command> getCommands(){
        return Collections.unmodifiableList(COMMANDS);
    }
    public static Command getCommand(String name){
        return COMMANDS.stream()
                .filter(command -> command.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
