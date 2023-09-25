package org.example.core.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {
        System.out.println("\u001B[32m" + "online...." + "\u001B[0m");
        System.out.println("\u001B[36m" + "prefix: " + System.getProperty("PREFIX") + "\u001B[0m");
    }

    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            return;
        }

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (content.equalsIgnoreCase( (System.getProperty("PREFIX")) + "ping")) {

            MessageChannel channel = event.getChannel();
            channel.sendMessage("pong!").queue();
        }
    }
}
