package dev.merciful.main.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class opCommand implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            if(args.length > 0){
                Player target = Bukkit.getPlayerExact(args[0]);
                Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE+args[0]);
                if(target instanceof Player){
                target.sendMessage(ChatColor.GREEN+"You've been granted Operator by Console!");
                target.setOp(true);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"You have granted "+target.getName()+" operator!");
                }else{
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Invalid Player!");
                }


            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED +"Invalid Usage\n/op [playername]");
            }

        } else {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.DARK_RED + "Only console can perform this command!");
        }



        return false;
    }
}