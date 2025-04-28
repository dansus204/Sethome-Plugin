package dansus204;

import dansus204.command.HomeCommand;
import dansus204.command.SetHomeCommand;
import dansus204.database.DatabaseConnector;
import dansus204.database.DatabaseExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sethome extends JavaPlugin {

    public static Sethome INSTANCE;

    private DatabaseExecutor databaseExecutor;

    @Override
    public void onEnable() {
        INSTANCE = this;
        // Plugin startup logic
        databaseExecutor = new DatabaseExecutor(new DatabaseConnector("jdbc:sqlite:sethome-db.sqlite"));

        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DatabaseExecutor getDatabaseExecutor() {
        return databaseExecutor;
    }
}
