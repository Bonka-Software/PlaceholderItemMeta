package gg.bonka.placeholderItemMeta.debugging;

import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Simple debug listener that gives the player a custom item with placeholders.
 */
@NoArgsConstructor
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ItemStack item = new ItemStack(Material.DIRT);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text("Your name is: %player_name%").decoration(TextDecoration.ITALIC, false));

        List<Component> lore = List.of(
                Component.empty(),
                MiniMessage.miniMessage().deserialize("<white>I know your name is: <green>%player_name%</green></white>").decoration(TextDecoration.ITALIC, false),
                MiniMessage.miniMessage().deserialize("<gray>Because I'm a packet based item!</gray>")
        );

        meta.lore(lore);
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }
}
