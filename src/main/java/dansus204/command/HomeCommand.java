package dansus204.command;

import dansus204.Sethome;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
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
            player.sendMessage(Component.text(Objects.requireNonNull(Sethome.getInstance().getConfig().getString("HOME_OVERWORLD_ERROR_MESSAGE"))));
            return false;
        }


        CompletableFuture.supplyAsync(() -> {
            Vector vector = Sethome.getInstance().getDatabaseExecutor().getPoint(player);
            if (vector == null) {
                player.sendMessage(Component.text(Objects.requireNonNull(Sethome.getInstance().getConfig().getString("NO_HOMEPOINT_MESSAGE"))));
                return false;
            }
            player.sendMessage(Component.text(Objects.requireNonNull(Sethome.getInstance().getConfig().getString("TELEPORT_START_MESSAGE"))));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Sethome.getInstance(), () -> {
                player.teleport(vector.toLocation(player.getWorld()));
                player.sendMessage(Component.text(Objects.requireNonNull(Sethome.getInstance().getConfig().getString("TELEPORT_FINISH_MESSAGE"))));
            }, 20L * Integer.parseInt(Objects.requireNonNull(Sethome.getInstance().getConfig().getString("TELEPORT_TIMER"))));
            return true;
        });

        return true;
    }
}
