package org.example.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

// https://discord.com/api/oauth2/authorize?client_id=1151248919842537513&permissions=8&scope=bot

public class ReadyListener implements EventListener
{
    public static void main(String[] args)
            throws InterruptedException
    {
        JDA jda = JDABuilder.createDefault("MTE1MTI0ODkxOTg0MjUzNzUxMw.GmXXqv.C893hxP_t3OfURcCNWJqfyiW5zS0vprmcjhETM", GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES).addEventListeners(new ReadyListener()).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.competing("Deranking!"));

        jda.addEventListener(new MyListener());
        jda.addEventListener(new Test());

        jda.awaitReady();
    }

    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }

    public static class Test extends ListenerAdapter
    {
        public void onMessageReceived(MessageReceivedEvent event)
        {
            System.out.println(event.getAuthor() + event.getMessage().getContentDisplay());
        }
    }

}
