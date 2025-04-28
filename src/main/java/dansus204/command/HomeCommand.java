package dansus204.command;

import dansus204.Sethome;
import dansus204.database.DatabaseConnector;
import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public final class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof final Player player)) {
            return false;
        }
        if (player.getWorld().getEnvironment() != World.Environment.NORMAL) {
            player.sendMessage(Component.text("Телепортироваться можно только в верхнем мире"));
            return false;
        }
        final Vector homePoint = Sethome.INSTANCE.getDatabaseExecutor().getPoint(player);
        if (homePoint == null) {
            player.sendMessage(Component.text("Точка дома не установлена"));
            return false;
        }

        player.teleport(homePoint.toLocation(player.getWorld()));
        player.sendMessage(Component.text("Телепортация прошла успешно"));

        return false;
    }
}
