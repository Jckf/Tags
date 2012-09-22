package no.jckf.tags;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class TagApiListener implements Listener {
	@EventHandler
	public void onVisible(PlayerReceiveNameTagEvent event) {
		event.setTag(event.getNamedPlayer().getPlayerListName());
	}
}
