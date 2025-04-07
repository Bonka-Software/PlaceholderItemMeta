# Placeholder Item Meta

Made for minecraft version `1.21.4`

[Available on Spigot](https://www.spigotmc.org/resources/placeholder-item-meta.123852/)

A minecraft plugin that allows you to use (per player) placeholders in item names and lore.
PIM uses protocolLib to intercept all outgoing item packets and replace any placeholders (e.g; %player_name%) in the name or lore of the item with PlaceholderAPI's data.
It's a very simple but effective plugin that also features some customizability to fit your specific needs.

Is there a feature missing, or are you running into a bug? Please consider opening an [issue](https://github.com/Bonka-Software/PlaceholderItemMeta/issues)!

![image](https://github.com/user-attachments/assets/5b18b36c-543f-4dac-b397-a4ea39aeda09)

---

#### Version 1.0.0
This version of PIM contains:
- Packet based placeholder rendering in item name and lore using [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) and [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
- Easy to use config, allowing you to whitelist certain data containers and block the use of placeholders when a player renames an item in an anvil.

---

## Dependencies
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)

---

## Developers

This plugin is currently a bit too simple for an API. Please consider opening an issue if you do need to access certain parts of the plugin, I'll be happy to help!

You are also free to fork this project for your own use or contribute to the project, I don't have strict guidelines, but please try to adept a bit to the current code style, this will keep the project easy to read :)
