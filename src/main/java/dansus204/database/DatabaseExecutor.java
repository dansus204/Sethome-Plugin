package dansus204.database;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.sql.*;

public class DatabaseExecutor {

    private final DatabaseConnector connector;
    private final Statement statement;

    public DatabaseExecutor(DatabaseConnector connector) {
        this.connector = connector;
        try {
            statement = connector.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS HomePoints(player_name TEXT, x REAL, y REAL, z REAL, PRIMARY KEY (player_name));");
        } catch (
        SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addPoint(Player player, Location location) {
        final String name = player.getName();

        try (PreparedStatement updateHome = connector.getConnection().prepareStatement(
                "REPLACE INTO HomePoints(player_name, x, y, z) VALUES(?, ?, ?, ?);" );
        ) {
            updateHome.setString(1, name);
            updateHome.setDouble(2, location.getX());
            updateHome.setDouble(3, location.getY());
            updateHome.setDouble(4, location.getZ());
            updateHome.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector getPoint(Player player) {
        final String name = player.getName();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM HomePoints WHERE player_name = '" + name + "';");
            if (resultSet.next()) {
                return new Vector(
                        resultSet.getDouble(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
