package dansus204.command;


import dansus204.Sethome;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class SetHomeCommand implements CommandExecutor {

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
        final Location location = player.getLocation();

        if (player.getWorld().getEnvironment() != World.Environment.NORMAL) {
            player.sendMessage(Component.text("Точку дома можно установить только в верхнем мире"));
            return false;
        }

        if (Sethome.INSTANCE.getDatabaseExecutor().addPoint(player, location)) {
            player.sendMessage(Component.text("Точка дома сохранена"));
        }


        return false;
    }
}
