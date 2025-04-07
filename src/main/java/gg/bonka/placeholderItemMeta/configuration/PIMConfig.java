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

    public PIMConfig() {
        super(PlaceholderItemMeta.getInstance().getDataFolder(), "config.yml");

        if(instance != null)
            throw new IllegalStateException("PIMConfig instance already exists!");

        instance = this;
    }

    @Override
    public void save() {
        put("whitelisted-persistent-data-containers", getWhitelistedPersistentDataContainers());

        try {
            super.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWhitelistedPersistentDataContainers() {
        return getStringList(whitelistedPersistentDataContainers, "whitelisted-persistent-data-containers");
    }

    private List<String> getStringList(List<String> array, String path) {
        if(array == null) {
            array = getStringList(path);
        }

        return array;
    }
}
