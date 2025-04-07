package gg.bonka.placeholderItemMeta.items.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class AnvilListener implements Listener {

    @EventHandler
    public void onRename(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.ANVIL)
            return;

        if(event.getCurrentItem() == null)
            return;

        if(!event.getSlotType().equals(InventoryType.SlotType.RESULT))
            return;

        String name = PlainTextComponentSerializer.plainText().serialize(event.getCurrentItem().effectiveName());
        int percentCount = name.length() - name.replace("%", "").length();

        if(percentCount < 2)
            return;

        event.setCancelled(true);
        ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        event.getWhoClicked().sendMessage(Component.text("Placeholders are not allowed!").color(NamedTextColor.RED));
    }
}
