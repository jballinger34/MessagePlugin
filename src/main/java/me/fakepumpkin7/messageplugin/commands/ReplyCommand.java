package me.fakepumpkin7.messageplugin.commands;

import com.sun.istack.internal.NotNull;
import me.fakepumpkin7.messageplugin.MessagePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {
    public MessagePlugin plugin;
    public ReplyCommand(MessagePlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            String senderName = ((Player) sender).getDisplayName();
            String receiverName = plugin.replyTo.get(senderName);
            if(receiverName != null){
                Player receiver = Bukkit.getPlayer(receiverName);
                if(receiver != null){
                    if(args != null){

                        String message = "";
                        for(int i=0;i<args.length;i++) {
                            message = message + args[i] + " ";
                        }
                        plugin.SendMessage((Player) sender,receiver,message);
                    }
                    else {
                        sender.sendMessage("Please add a message to send");
                        return true;
                    }
                } else {
                    sender.sendMessage("Cannot Find Player With Name " + receiverName);
                    return true;
                }
            } else {
                sender.sendMessage("You do not have any messages to reply to");
                return true;
            }
        }


        return true;
    }
}
