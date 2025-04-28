package dansus204;

import dansus204.command.HomeCommand;
import dansus204.command.SetHomeCommand;
import dansus204.database.DatabaseConnector;
import dansus204.database.DatabaseExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sethome extends JavaPlugin {

    @Override
    public void onEnable() {

        DatabaseExecutor databaseExecutor = new DatabaseExecutor(new DatabaseConnector("jdbc:sqlite:sethome-db.sqlite"));

        getCommand("sethome").setExecutor(new SetHomeCommand(databaseExecutor, this));
        getCommand("home").setExecutor(new HomeCommand(databaseExecutor, this));

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
