package me.fakepumpkin7.messageplugin;

import me.fakepumpkin7.messageplugin.commands.MsgCommand;
import me.fakepumpkin7.messageplugin.commands.ReplyCommand;
import me.fakepumpkin7.messageplugin.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class MessagePlugin extends JavaPlugin {


    //block and unblock command needed
    //blocklist command needed as well
    public HashMap<String,String> replyTo = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("by FakePumpkin7");
        //register commands
        this.getCommand("message").setExecutor(new MsgCommand(this));
        this.getCommand("reply").setExecutor(new ReplyCommand(this));

        //player join listener to add player to replyto hashmap on join and removes on leave
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void SendMessage(Player sender, Player receiver, String message){
        String receiverName = receiver.getDisplayName();
        String senderName = sender.getDisplayName();
        //display message in senders chat Me->receiver: message
        sender.sendMessage(ChatColor.BLUE + "Me -> "+ ChatColor.YELLOW + receiverName+ ChatColor.WHITE+ ": "  + message);
        //display message in receivers chat sender->Me: message
        receiver.sendMessage(ChatColor.YELLOW + senderName+ ChatColor.BLUE +" -> Me"+ ChatColor.WHITE +": "+ message );
        //update storage of last message receiver for receiver
        this.replyTo.put(receiverName, senderName);
    }
}
