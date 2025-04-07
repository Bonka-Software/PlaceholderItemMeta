package gg.bonka.placeholderItemMeta.items.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import gg.bonka.placeholderItemMeta.PlaceholderItemMeta;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemPacketListener {

    public ItemPacketListener() {
        // 0x13 Set container content
        // https://minecraft.wiki/w/Java_Edition_protocol#Set_Container_Content
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(PlaceholderItemMeta.getInstance(), PacketType.Play.Server.WINDOW_ITEMS) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                List<ItemStack> packetItems = event.getPacket().getItemListModifier().read(0);
                List<ItemStack> items = new ArrayList<>(packetItems.size());

                for(ItemStack item : packetItems) {
                    items.add(parseItem(event.getPlayer(), item));
                }

                packet.getItemListModifier().write(0, items);
                event.setPacket(packet);
            }
        });

        // 0x15 Set Container Slot
        // https://minecraft.wiki/w/Java_Edition_protocol#Set_Container_Slot
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(PlaceholderItemMeta.getInstance(), PacketType.Play.Server.SET_SLOT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                ItemStack item = parseItem(event.getPlayer(), event.getPacket().getItemModifier().read(0));

                packet.getItemModifier().write(0, item);
                event.setPacket(packet);
            }
        });
    }

    private ItemStack parseItem(Player player, ItemStack item) {
        if(item == null || !item.hasItemMeta())
            return null;

        ItemMeta meta = item.getItemMeta();

        String itemName = PlaceholderAPI.setPlaceholders(player, MiniMessage.miniMessage().serialize(item.effectiveName()));
        meta.itemName(MiniMessage.miniMessage().deserialize(itemName));

        if(meta.hasDisplayName()) {
            String displayName = PlaceholderAPI.setPlaceholders(player, MiniMessage.miniMessage().serialize(Objects.requireNonNull(meta.displayName())));
            meta.displayName(MiniMessage.miniMessage().deserialize(displayName));
        }

        List<Component> lore = meta.lore();
        if(lore != null) {
            ArrayList<Component> newLore = new ArrayList<>();

            for(Component component : lore) {
                String line = PlaceholderAPI.setPlaceholders(player, MiniMessage.miniMessage().serialize(component));
                newLore.add(MiniMessage.miniMessage().deserialize(line));
            }

            meta.lore(newLore);
        }

        item.setItemMeta(meta);
        return item;
    }
}
