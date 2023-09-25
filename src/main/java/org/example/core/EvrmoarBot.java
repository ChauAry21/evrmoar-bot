package org.example.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.example.core.listeners.MyListener;

import javax.security.auth.login.LoginException;

public class EvrmoarBot {

    private final ShardManager shardControl;
    private final Dotenv config;
    public EvrmoarBot() throws LoginException {
        config = Dotenv.configure().load();

        DefaultShardManagerBuilder build = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));

        build.setStatus(OnlineStatus.ONLINE);
        build.setActivity(Activity.competing("Deranking!"));
        build.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
        shardControl = build.build();

        shardControl.addEventListener(new MyListener());
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
