package dansus204;

import dansus204.command.HomeCommand;
import dansus204.command.SetHomeCommand;
import dansus204.database.DatabaseConnector;
import dansus204.database.DatabaseExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sethome extends JavaPlugin {

    private static Sethome insnatce;

    private DatabaseExecutor databaseExecutor;


    @Override
    public void onEnable() {
        insnatce = this;

        databaseExecutor = new DatabaseExecutor(new DatabaseConnector("jdbc:sqlite:sethome-db.sqlite"));

        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Sethome getInstance() {
        return insnatce;
    }

    public DatabaseExecutor getDatabaseExecutor() {
        return databaseExecutor;
    }
}
