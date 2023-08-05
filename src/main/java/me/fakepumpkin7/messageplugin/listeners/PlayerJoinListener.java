package me.fakepumpkin7.messageplugin.listeners;

import me.fakepumpkin7.messageplugin.MessagePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    private MessagePlugin plugin;
    public PlayerJoinListener(MessagePlugin plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String name = player.getDisplayName();
        plugin.replyTo.put(name,null);
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String name = player.getDisplayName();
        plugin.replyTo.remove(name);
    }

}
