package brcomkassin.mathevent.operations;

import brcomkassin.mathevent.cache.MathManager;
import brcomkassin.utils.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

public class MathOperations {
    @Getter
    @Setter
    private static final List<Long> NUMBERS = MathManager.getNUMBER_LIST();
    @Getter
    @Setter
    private static boolean eventActive = false;

    public static void startRandomOperation(Player player) {
        MathOperationType operation = MathOperationType.values()[ThreadLocalRandom.current().nextInt(MathOperationType.values().length)];

        switch (operation) {
            case ADDITION -> additionOperation(player);
            case SUBTRACTION -> subtractionOperation(player);
            case MULTIPLICATION -> multiplyOperation(player);
            case DIVISION -> divisionOperation(player);
        }
    }

    public static boolean resultOperation(long number) {
        if (NUMBERS.isEmpty()) return false;
        return NUMBERS.contains(number);
    }

    private static void additionOperation(Player player) {
        startOperation(player, "+", Long::sum);
    }

    private static void subtractionOperation(Player player) {
        startOperation(player, "-", (a, b) -> a - b);
    }

    private static void multiplyOperation(Player player) {
        startOperation(player, "*", (a, b) -> a * b);
    }

    private static void divisionOperation(Player player) {
        long a = numberGenerator(2, 6); // Garante que A seja maior que 1
        long b = numberGenerator(1, a); // Garante que B seja divisor de A
        long newNumber = a / b;
        NUMBERS.add(newNumber);
        setEventActive(true);
        Message.Chat.sendAllPlayers("&aQual o valor da divisão de &f" + a + " &a/ &f" + b + " ?");
    }

    private static void startOperation(Player player, String symbol, BiFunction<Long, Long, Long> operation) {
        long a = numberGenerator(1, 99999999);
        long b = numberGenerator(1, 99999999);
        long result = operation.apply(a, b);
        NUMBERS.add(result);
        setEventActive(true);
        Message.Chat.sendAllPlayers("&aQual o valor da operação: &f" + a + " " + symbol + " " + b + " ?");
        Message.Chat.send(player, "&aResultado da operação:&f " + result);
    }

    private static long numberGenerator(final long from, final long util) {
        return ThreadLocalRandom.current().nextLong(from, util + 1); // Inclui o valor máximo
    }

    public static void giveReward(Player player) {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
    }
}
