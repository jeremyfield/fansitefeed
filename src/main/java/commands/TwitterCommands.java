package commands;

import config.Config;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserList;
import twitter4j.api.ListsResources;

import javax.json.JsonObject;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TwitterCommands implements CommandExecutor{

    JsonObject config;
    Twitter twitter;

    public TwitterCommands() {
        this.config = Config.getConfig("/config.json");
        this.twitter = TwitterFactory.getSingleton();
    }

    @Command(aliases = {"!fansites", "!list", "!accounts"},
             privateMessages = false,
             description = "Gives the link to the list of fansites followed by the Twitter Strean.",
             usage = "!fansites")
    public String onFansitesCommand() {
        String screenName = config.getString("listScreenName");
        String slug = config.getString("listSlug");
        StringBuilder builder = new StringBuilder();
        builder.append("The list of fansites can be found here: " + System.lineSeparator());
        builder.append("<https://twitter.com/" + screenName + "/lists/" + slug + "/members>");
        return builder.toString();
    }

    @Command(aliases = {"!follow", "!add"},
             privateMessages = false,
             description = "Adds a fansite to the Twitter Stream.",
             usage = "!follow <fansite(s)>")
    public String onFollowCommand(IDiscordClient client, IUser user, String[] fansites) {
        if(!hasPermission(client, user)) { return "You do not have permission to execute that command."; }
        try {
            twitter.createUserListMembers(config.getString("listScreenName"), config.getString("listSlug"), fansites);
            return "Added users to the Twitter Stream";
        } catch (TwitterException e) {
            return "Something went wrong while trying to remove a fansite to the Twitter Stream.";
        }
    }

    @Command(aliases = {"!unfollow", "!delete", "!remove"},
             privateMessages = false,
             description = "Deletes a fansite from the Twitter Stream.",
             usage = "!unfollow <fansite(s)>")
    public String onUnfollowCommand(IDiscordClient client, IUser user, String[] fansites) {
        if(!hasPermission(client, user)) { return "You do not have permission to execute this command."; }
        try {
            twitter.destroyUserListMembers(config.getString("listScreenName"), config.getString("listSlug"), fansites);
            return "Deleted users from the Twitter Stream.";
        } catch (TwitterException e) {
            e.printStackTrace();
            return "Something went wrong while trying to add a fansite to the Twitter Stream.";
        }
    }

    private boolean hasPermission(IDiscordClient client, IUser user) {
        IRole contributor = client.getRoles()
                                  .stream()
                                  .filter(role -> role.getName().equalsIgnoreCase("contributor"))
                                  .findFirst()
                                  .get();
        return user.hasRole(contributor);
    }
}
