package xyz.kamaii.deployer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class DeployerConfig
{
    private final String discordToken;
    private final String relayChannel;
    public  DeployerConfig(String discordToken, String relayChannel) {
        this.discordToken = discordToken;
        this.relayChannel = relayChannel;
    }

    public String getDiscordToken() {
        return discordToken;
    }

    public String getRelayChannel() {
        return relayChannel;
    }
}