package brcomkassin.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.Arrays;

public interface Message {
    class Chat {
        public static void send(Player player, String... message) {
            Arrays.stream(message)
                    .map(string -> ChatColor.translateAlternateColorCodes('&', string))
                    .forEach(player::sendMessage);
        }

        public static void sendAllPlayers(String... message) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                send(player, message);
            }
        }
    }

    class ActionBar {
        public static void send(Player player, String message) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }
    }

}