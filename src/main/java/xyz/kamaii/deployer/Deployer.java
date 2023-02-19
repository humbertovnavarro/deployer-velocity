package xyz.kamaii.deployer;

import com.google.inject.Inject;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "deployer",
        name = "Deployer",
        version = BuildConstants.VERSION
)
public class Deployer {
    private JDA jda;
    private final Path dataDirectory;
    private final ProxyServer server;
    private final Logger logger;
    private final DeployerConfig config;

    @Inject
    public Deployer(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) throws Exception {
        this.config = DeployerConfigGson.load(dataDirectory.resolve("deployer.json"));
        this.jda = JDABuilder.createDefault(config.getDiscordToken()).build();
        this.server = server;
        this.logger = logger;
        logger.info("Starting deployer");
        this.dataDirectory = dataDirectory;
        server.getEventManager().register(this, PlayerChatEvent.class, event -> {
            logger.info(event.getPlayer().getUsername() + ": " + event.getMessage());
        });
    }
}
