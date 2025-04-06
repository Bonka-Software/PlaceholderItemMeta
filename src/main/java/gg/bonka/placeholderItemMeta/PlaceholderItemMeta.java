package gg.bonka.placeholderItemMeta;

import gg.bonka.placeholderItemMeta.logging.ConsoleLogger;
import lombok.Getter;
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
        ConsoleLogger.info(String.format("PlaceholderItemMeta [%s] has been enabled!", version));
    }

    @Override
    public void onDisable() {
        ConsoleLogger.info(String.format("Disabling PlaceholderItemMeta [%s]", version));
    }
}
