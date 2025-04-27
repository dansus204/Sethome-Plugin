package dansus204.database;

import dansus204.database.data.HomePoint;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.*;

public class DatabaseManager {
    public static final DatabaseManager instance = new DatabaseManager();

    private final Connection connection;
    private final Statement statement;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sethome-db.sqlite");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS HomePoints(player_name TEXT, x REAL, y REAL, z REAL);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPoint(Player player, Location location) {
        final String name = player.getName();

        try {
            statement.execute("REPLACE INTO HomePoints(player_name, x, y, z) VALUES('" + name
                    + "', " + location.getX()
                    + ", " + location.getY()
                    + ", " + location.getZ() + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HomePoint getPoint(Player player) {
        final String name = player.getName();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM HomePoints WHERE player_name = '" + name + "';");
            while (resultSet.next()) {
                return new HomePoint(
                        resultSet.getFloat(2),
                        resultSet.getFloat(3),
                        resultSet.getFloat(4)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
