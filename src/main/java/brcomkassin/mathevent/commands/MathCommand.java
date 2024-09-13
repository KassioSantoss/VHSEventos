package brcomkassin.mathevent.commands;

import brcomkassin.mathevent.operations.MathAddition;
import brcomkassin.mathevent.task.MathTask;
import brcomkassin.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MathCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length < 1) {
            incorrectCommand(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "iniciar":
                MathAddition.additionOperation(5, 5);
                MathTask.getInstance().start();
                break;
            case "cancelar":
                MathTask.getInstance().stopTask();
                break;
            default:
                incorrectCommand(player);
                break;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

    private void incorrectCommand(Player player) {
        Message.Chat.send(player, "Você pode usar /matematica help para obter mais informações sobre o comando");
    }
}
