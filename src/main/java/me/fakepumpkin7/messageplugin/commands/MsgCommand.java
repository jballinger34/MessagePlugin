package me.fakepumpkin7.messageplugin.commands;

import com.sun.istack.internal.NotNull;
import me.fakepumpkin7.messageplugin.MessagePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    public MessagePlugin plugin;
    public MsgCommand(MessagePlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length > 1){

                //check to see if command has an online player to send to
                String receiverName = args[0];
                Player receiver = Bukkit.getPlayer(receiverName);
                if(receiver == null){
                    sender.sendMessage("Cannot Find Player With Name " + receiverName);
                    return true;
                }
                //check if message exists
                String message = "";

                //construct message from list of args
                for(int i=1;i<args.length;i++) {
                    message = message + args[i] + " ";
                }
                plugin.SendMessage((Player) sender,receiver,message);
                return true;
            }
            else return false;
            //need a way to store last sender for reply
        }
        return true;
    }

}
