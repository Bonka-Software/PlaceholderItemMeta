package gg.bonka.placeholderItemMeta;

import co.aikar.commands.PaperCommandManager;
import gg.bonka.placeholderItemMeta.commands.PIMCommand;
import gg.bonka.placeholderItemMeta.configuration.PIMConfig;
import gg.bonka.placeholderItemMeta.debugging.PlayerJoinListener;
import gg.bonka.placeholderItemMeta.items.listener.ItemPacketListener;
import gg.bonka.placeholderItemMeta.logging.ConsoleLogger;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlaceholderItemMeta extends JavaPlugin {

    private final static String version = "1.0.0";

    @Getter
    private static PlaceholderItemMeta instance;

    @Override
    public void onEnable() {
        if(instance != null)
            throw new IllegalStateException("PlaceholderItemMeta instance already exists!");

        instance = this;
        new PIMConfig().save();

        new ItemPacketListener();

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new PIMCommand());

        ConsoleLogger.info(String.format("PlaceholderItemMeta [%s] has been enabled!", version));
    }

    @Override
    public void onDisable() {
        ConsoleLogger.info(String.format("Disabling PlaceholderItemMeta [%s]", version));
    }
}
