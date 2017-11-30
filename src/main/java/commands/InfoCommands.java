package commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

import java.time.Duration;
import java.time.Instant;

public class InfoCommands implements CommandExecutor {

    public Instant timeCreated;

    public InfoCommands() {
        timeCreated = Instant.now();
    }

    @Command(aliases = {"!uptime", "!status"},
             privateMessages = false,
             description = "Shows how long the bot has been running.",
             usage = "!uptime")
    public String onUptimeCommand() {
        Duration duration = Duration.between(timeCreated, Instant.now());
        return duration.toString();
    }
}
