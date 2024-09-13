package brcomkassin.mathevent.task;

import brcomkassin.VHSPlugin;
import brcomkassin.mathevent.cache.MathCollections;
import brcomkassin.utils.Message;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathTask extends BukkitRunnable {

    private int count = 0;
    private static MathTask instance;

    public static MathTask getInstance() {
        if (instance == null) {
            instance = new MathTask();
        }
        return instance;
    }

    public void start() {
        runTaskTimer(VHSPlugin.getInstance(), 0, 20);
    }

    public void stopTask() {
        cancel();
        instance = null;
        clearCollection();
    }

    public void stopTaskForEvent(String winnerName) {
        cancel();
        instance = null;
        clearCollection();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Message.Chat.send(player, "&aO jogador " + winnerName + " acertou o cálculo e ganhou uma recompensa!");
        }
    }

    @Override
    public void run() {
        if (MathCollections.getNumberList().isEmpty() || count == 20) {
            stopTask();
            for (Player player : Bukkit.getOnlinePlayers()) {
                Message.Chat.send(player, "&4 Tempo para resposta esgotado! Ninguém ganhou premiação");
            }
            return;
        }
        System.out.println(count);
        count++;
    }

    private void clearCollection() {
        MathCollections.getUuidList().clear();
        MathCollections.getNumberList().clear();
    }

}
