import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.Discord4JHandler;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import java.util.Optional;

public class FansiteBot {

    private String token;
    private Optional<IDiscordClient> discordClient;

    public FansiteBot(String token) {
        this.token = token;
        this.discordClient = Optional.empty();
    }

    public void login() {
        ClientBuilder clientBuilder = new ClientBuilder().withToken(token);
        discordClient = Optional.ofNullable(clientBuilder.login());
    }

    public void registerCommands(CommandExecutor[] commandExecutors) {
        discordClient.ifPresent(client -> {
            CommandHandler commandHandler = new Discord4JHandler(client);
            for(CommandExecutor executor : commandExecutors) {
                commandHandler.registerCommand(executor);
            }
        });
    }
}
