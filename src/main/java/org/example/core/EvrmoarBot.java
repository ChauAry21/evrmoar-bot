package org.example.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.core.listeners.MyListener;

import javax.security.auth.login.LoginException;

public class EvrmoarBot {

    private final ShardManager shardControl;
    private final Dotenv config;



    public ShardManager getShardControl() {
        return shardControl;
    }

    public EvrmoarBot() throws LoginException {
        config = Dotenv.configure().systemProperties().load();

        DefaultShardManagerBuilder build = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));

        build.setStatus(OnlineStatus.ONLINE);
        build.setActivity(Activity.competing("Deranking!"));
        build.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
        build.setMemberCachePolicy(MemberCachePolicy.ALL);
        build.setChunkingFilter(ChunkingFilter.ALL);
        build.enableCache(CacheFlag.ONLINE_STATUS);

        shardControl = build.build();
        shardControl.addEventListener(new MyListener());
    }

    public static void main(String[] args) {
        try {
            EvrmoarBot evrmoar = new EvrmoarBot();
        } catch (LoginException e) {
            System.out.println("\u001B[31m" + "INVALID BOT TOKEN!" + "\u001B[0m");
        } catch (InvalidTokenException e) {
            System.out.println("\u001B[31m" + "INVALID BOT TOKEN!" + "\u001B[0m");
        }
    }

}
