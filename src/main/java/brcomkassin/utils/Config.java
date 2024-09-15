package brcomkassin.utils;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

@Getter
public class Config  extends YamlConfiguration {

    private File file;
    private String name;
    private JavaPlugin plugin;

    public Config(JavaPlugin plugin, String name) {
        file = new File((this.plugin = plugin).getDataFolder(), this.name = name);
    }

    public void saveConfig() {
        try {
            save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reloadConfig() {
        try {
            load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveDefaultConfig() {
        plugin.saveResource(name, false);
        reloadConfig();
    }
}