package org.example.core.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Purge extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.equalsIgnoreCase((System.getProperty("PREFIX")) + "purge")) {

            event.getChannel().purgeMessages().get(20);
        }
    }
}
