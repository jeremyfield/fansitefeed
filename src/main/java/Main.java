import commands.InfoCommands;
import commands.TwitterCommands;
import de.btobastian.sdcf4j.CommandExecutor;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Missing discord token as first argument.");
            return;
        }

        FansiteBot fansiteBot = new FansiteBot(args[0]);
        CommandExecutor[] commandExecutors = {new InfoCommands(), new TwitterCommands()};

        fansiteBot.login();
        fansiteBot.registerCommands(commandExecutors);
    }
}
