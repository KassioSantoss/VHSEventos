package brcomkassin.mathevent.events;

import brcomkassin.mathevent.operations.MathOperations;
import brcomkassin.mathevent.rewards.RewardManager;
import brcomkassin.mathevent.task.MathTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MathChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if (!MathOperations.isEventActive()) return;

        long number;

        try {
            number = Long.parseLong(message);
        } catch (NumberFormatException e) {
            return;
        }

        if (!MathOperations.resultOperation(number)) return;

        MathTask.getInstance().stopTaskForEvent(player.getName());
        RewardManager.getInstance().giveReward(player);
        event.setCancelled(true);
    }

}
