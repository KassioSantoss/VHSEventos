package brcomkassin.mathevent.rewards;

import brcomkassin.VHSPlugin;
import brcomkassin.utils.Config;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RewardManager {
    @Getter
    private static final List<String> COMMANDS_LIST = new ArrayList<>();
    private final Config config = VHSPlugin.getInstance().getConfig();
    private static RewardManager instance;

    public static RewardManager getInstance() {
        if (instance == null) {
            instance = new RewardManager();
        }
        return instance;
    }

    public void addCommand(String command) {
        List<String> commands = config.getStringList("Recompensas.commands");
        COMMANDS_LIST.add(command);
        config.set("Recompensas.commands", COMMANDS_LIST);
        config.saveConfig();
    }

    public void giveReward(Player player) {
        if (COMMANDS_LIST.isEmpty()) return;

        Bukkit.getScheduler().runTask(VHSPlugin.getInstance(), () -> {
            for (String command : COMMANDS_LIST) {
                String formattedCommand = command.replace("playerName", player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), formattedCommand);
            }
        });
    }

    public void reloadRewards() {
        ConfigurationSection configurationSection = config.getConfigurationSection("Recompensas");

        if (configurationSection == null) return;

        List<String> stringList = config.getStringList("Recompensas.commands");

        if (stringList.isEmpty()) return;
        COMMANDS_LIST.addAll(stringList);
    }
}
