import com.TicketTV.Listeners.ButtonListeners;
import com.TicketTV.Listeners.CommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

@SuppressWarnings("ALL")
public class Main extends ListenerAdapter {
    private final ShardManager shardManager;

    public Main() throws LoginException {
        String token = "token";

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.watching("Tickets"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        shardManager = builder.build();
        System.out.println("Bot Started");

        shardManager.addEventListener(new CommandManager(), new ButtonListeners());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            Main bot = new Main();
        } catch (LoginException e) {
            System.out.println("Invalid Bot Token");
        }
    }
}
