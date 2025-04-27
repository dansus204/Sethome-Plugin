package dansus204;

import dansus204.command.HomeCommand;
import dansus204.command.SetHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sethome extends JavaPlugin {

    public static Sethome INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        // Plugin startup logic


        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
