import commands.InfoCommands;
import de.btobastian.sdcf4j.CommandExecutor;

public class Main {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Missing discord token as first argument.");
            return;
        }

        FansiteBot fansiteBot = new FansiteBot(args[0]);
        CommandExecutor[] commandExecutors = {new InfoCommands()};

        fansiteBot.login();
        fansiteBot.registerCommands(commandExecutors);
    }
}
