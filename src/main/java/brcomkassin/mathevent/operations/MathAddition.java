package brcomkassin.mathevent;

import brcomkassin.mathevent.cache.MathCollections;
import brcomkassin.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Locale;

public class MathAddition {

    private static final List<Long> numbers = MathCollections.getNumberList();

    public static void additionOperation(long a, long b) {
        if (!numbers.isEmpty()) return;
        long newNumber = a + b;
        numbers.add(newNumber);

        for (Player player : Bukkit.getOnlinePlayers()) {
            Message.Chat.send(player, "&aQual o valor da soma de &f" + a + " &a+ &f" + b + " ?");
        }
    }

    public static boolean resultOperation(Long number) {
        return number.equals(numbers.get(0));
    }

    public static void giveReward(Player player) {
        if (player == null) return;

        player.getInventory().addItem(new ItemStack(Material.GOLD_BLOCK));
        Message.Chat.send(player, "Parabéns, você acertou o cálculo");
    }

}

