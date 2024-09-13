package brcomkassin;

import brcomkassin.mathevent.commands.MathCommand;
import brcomkassin.mathevent.events.MathChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VHSPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MathChatListener(), this);
        getCommand("matematica").setExecutor(new MathCommand());
    }

    @Override
    public void onDisable() {

    }

    public static VHSPlugin getInstance() {
        return getPlugin(VHSPlugin.class);
    }

}
