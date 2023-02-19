package xyz.kamaii.deployer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DeployerConfigGson {
    public static String serializeDeployerConfig(DeployerConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(config);
    }
    public static DeployerConfig deserializeDeployerConfig(String config) {
        Gson gson = new Gson();
        return gson.fromJson(config, DeployerConfig.class);
    }
    public static DeployerConfig createDefault(File configFile) throws IOException {
        DeployerConfig config =  new DeployerConfig("your-token-here", "relay-channel-id");
        FileWriter writer = new FileWriter(configFile);
        writer.write(DeployerConfigGson.serializeDeployerConfig(config));
        writer.close();
        return  config;
    }
    public static DeployerConfig load(@DataDirectory Path dataDirectory) throws IOException {
        File configFile = new File(dataDirectory.resolve("deployer.json").toUri());
        boolean newFile = configFile.mkdirs() || configFile.createNewFile();
        if (newFile) {
           return DeployerConfigGson.createDefault(configFile);
        }
        Scanner reader = new Scanner(configFile);
        StringBuilder configFileContents = new StringBuilder();
        while(reader.hasNextLine()) {
            configFileContents.append(reader.nextLine());
        }
        reader.close();
        return DeployerConfigGson.deserializeDeployerConfig(configFileContents.toString());
    }
}
