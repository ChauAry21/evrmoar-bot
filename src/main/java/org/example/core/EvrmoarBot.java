package org.example.core;

import io.github.cdimascio.dotenv.Dotenv;
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
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;



//config = Dotenv.configure().load();
public class EvrmoarBot {

    private final ShardManager shardControl;
    private final Dotenv config;
    public EvrmoarBot() throws LoginException {
        config = Dotenv.configure().load();

        DefaultShardManagerBuilder build = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));
        build.setStatus(OnlineStatus.ONLINE);
        build.setActivity(Activity.competing("Deranking!"));
        shardControl = build.build();
    }

    public Dotenv getConfig() {
        return config;
    }

    public ShardManager getShardControl() {
        return shardControl;
    }

    public static void main(String[] args) {
        try {
            EvrmoarBot evrmoar = new EvrmoarBot();
        } catch (LoginException e) {
            System.out.println("INVALID BOT TOKEN!");
        }
    }

}
