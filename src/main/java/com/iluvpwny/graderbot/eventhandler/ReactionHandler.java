package com.iluvpwny.graderbot.eventhandler;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class ReactionHandler implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(event instanceof MessageReactionAddEvent){
            MessageReactionAddEvent reactEvent = (MessageReactionAddEvent) event;
            if(reactEvent.getReaction().getReactionEmote().getName().equals("❌") && !reactEvent.getUser().equals(reactEvent.getJDA().getSelfUser())){
                Message message = reactEvent.getChannel().retrieveMessageById(reactEvent.getMessageIdLong()).complete();
                if(message.getAuthor().equals(event.getJDA().getSelfUser())){
                    message.delete().queue();
                }
            }
            //System.out.println(reactEvent.getReaction().getReactionEmote().getName());

        }
    }
}
