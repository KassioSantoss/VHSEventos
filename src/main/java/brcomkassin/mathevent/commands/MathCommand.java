package brcomkassin.mathevent.commands;

import brcomkassin.mathevent.operations.MathOperations;
import brcomkassin.mathevent.rewards.RewardManager;
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
            case "setrecompensa":
                StringBuilder newCommand = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    newCommand.append(args[i]).append(" ");
                }
                RewardManager.getInstance().addCommand(newCommand.toString().trim());
                Message.Chat.send(player, "&aComando adicionado: &f" + newCommand);
                break;

            case "iniciar":
            case "init":
                if (MathOperations.isEventActive()) {
                    Message.Chat.send(player, "&4 O evento já está ativo. Você pode usar /matematica cancelar para parar o evento.");
                    return false;
                }
                MathOperations.startRandomOperation(player);
                MathTask.getInstance().start();
                break;

            case "cancelar":
            case "stop":
                if (!MathOperations.isEventActive()) {
                    Message.Chat.send(player, "&4O evento de matemática não está ativado.");
                    return false;
                }
                Message.Chat.send(player, "&4Evento cancelado com sucesso!");
                MathTask.getInstance().stopTask();
                break;

            default:
                incorrectCommand(player);
                break;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("iniciar", "init", "cancelar", "stop", "setrecompensa");
        }
        return List.of();
    }

    private void incorrectCommand(Player player) {
        Message.Chat.send(player, "Você pode usar /matematica help para obter mais informações sobre o comando");
    }
}
