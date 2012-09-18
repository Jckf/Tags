package no.jckf.tags;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Tags extends JavaPlugin implements Listener {
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(this,this);

		if (pm.getPlugin("TagAPI") != null) {
			pm.registerEvents(new TagApiListener(),this);
		}

		for (Player player : getServer().getOnlinePlayers()) {
			if (player.isOp()) {
				tag(player);
			}
		}
	}

	public void onDisable() {
		for (Player player : getServer().getOnlinePlayers()) {
			untag(player);
		}
	}

	public void tag(Player player) {
		player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.RESET);
		player.setPlayerListName(ChatColor.RED + player.getName());
	}

	public void untag(Player player) {
		player.setDisplayName(player.getName());
		player.setPlayerListName(player.getName());
	}

	public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
		if (!sender.isOp()) {
			return true;
		}

		if (args.length != 1) {
			return false;
		}

		OfflinePlayer target = getServer().getOfflinePlayer(args[0]);

		if (command.getName().equalsIgnoreCase("op")) {
			target.setOp(true);
			sender.sendMessage("Opped " + target.getName());

			if (target.isOnline()) {
				tag(target.getPlayer());
			}
		} else if (command.getName().equalsIgnoreCase("deop")) {
			target.setOp(false);
			sender.sendMessage("De-opped " + target.getName());

			if (target.isOnline()) {
				untag(target.getPlayer());
			}
		}

		return true;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		if (player.isOp()) {
			tag(player);
		}
	}
}
