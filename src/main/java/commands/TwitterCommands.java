package commands;

import config.Config;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import javax.json.JsonObject;

public class TwitterCommands implements CommandExecutor{

    JsonObject config;

    public TwitterCommands() {
        this.config = Config.getConfig("/config.json");
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
}
