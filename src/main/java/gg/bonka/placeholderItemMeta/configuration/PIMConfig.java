package gg.bonka.placeholderItemMeta.configuration;

import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
public class PIMConfig extends CustomConfig {

    @Getter
    private static PIMConfig instance;

    private List<String> whitelistedPersistentDataContainers;
    private Boolean blockAnvilPlaceholders;

    public PIMConfig() {
        super(PlaceholderItemMeta.getInstance().getDataFolder(), "config.yml");

        instance = this;
    }

    public static void reload() {
        new PIMConfig();
    }

    @Override
    public void save() {
        put("whitelisted-persistent-data-containers", getWhitelistedPersistentDataContainers());
        put("block-anvil-placeholders", getBlockAnvilPlaceholders());

        try {
            super.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWhitelistedPersistentDataContainers() {
        return getStringList(whitelistedPersistentDataContainers, "whitelisted-persistent-data-containers");
    }

    public boolean getBlockAnvilPlaceholders() {
        return getBoolean(blockAnvilPlaceholders, "block-anvil-placeholders");
    }

    private boolean getBoolean(Boolean bool, String key) {
        if(bool == null) {
            bool = Boolean.parseBoolean(getStringKey(key));
        }

        return Boolean.TRUE.equals(bool);
    }

    private List<String> getStringList(List<String> array, String path) {
        if(array == null) {
            array = getStringList(path);
        }

        return array;
    }
}
