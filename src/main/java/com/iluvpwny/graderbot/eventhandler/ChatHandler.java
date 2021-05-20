package com.iluvpwny.graderbot.eventhandler;

import com.iluvpwny.graderbot.command.Command;
import com.iluvpwny.graderbot.command.CommandManager;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ChatHandler implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(event instanceof MessageReceivedEvent){
            MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;
            if(!messageEvent.getMessage().getContentRaw().startsWith(Command.getPrefix()))return;
            String[] split = messageEvent.getMessage().getContentRaw().trim().replaceAll("\\s+"," ").split(" ");
            Command cmd = CommandManager.getCommand(split[0].substring(Command.getPrefix().length()));
            if(cmd !=null)cmd.execute(Arrays.copyOfRange(split,1,split.length),messageEvent.getMessage());
        }
    }
}
