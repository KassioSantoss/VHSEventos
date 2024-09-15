package brcomkassin;

import brcomkassin.mathevent.commands.MathCommand;
import brcomkassin.mathevent.events.MathChatListener;
import brcomkassin.mathevent.rewards.RewardManager;
import brcomkassin.utils.Config;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

@Getter
public final class VHSPlugin extends JavaPlugin {
    private Config config;

    @Override
    public void onEnable() {
        config = new Config(this, "config.yml");
        config.saveDefaultConfig();

        RewardManager.getInstance().reloadRewards();

        registerEvents(
                new MathChatListener()
        );

        registerCommands();

        getLogger().log(Level.INFO , "VHSEventos Inicializado com sucesso");
    }

    @Override
    public void onDisable() {
        config.saveConfig();
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands() {
        getCommand("matematica").setExecutor(new MathCommand());
    }


    public static VHSPlugin getInstance() {
        return getPlugin(VHSPlugin.class);
    }

}
