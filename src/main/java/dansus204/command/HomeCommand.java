package dansus204.command;

import dansus204.Sethome;
import dansus204.database.DatabaseConnector;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

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


        CompletableFuture.supplyAsync(() -> {
            Vector vector = Sethome.INSTANCE.getDatabaseExecutor().getPoint(player);
            if (vector == null) {
                player.sendMessage(Component.text("Точка дома не установлена"));
                return false;
            }
            player.sendMessage(Component.text("Телепортация..."));
            Bukkit.getScheduler().callSyncMethod(Sethome.INSTANCE, () ->
                    player.teleport(vector.toLocation(player.getWorld())));
            player.sendMessage(Component.text("Телепортация прошла успешно"));
            return true;
        });

        return true;
    }
}
