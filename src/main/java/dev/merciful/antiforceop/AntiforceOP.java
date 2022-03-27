package dev.merciful.antiforceop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import java.util.List;

public final class AntiforceOP extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "AntiforceOP has started!");
        this.saveDefaultConfig();
        //Listeners
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "AntiforceOP has stopped!");
    }

    List<String> op = getConfig().getStringList("Allowed operators");
    List<String> gm = getConfig().getStringList("Allowed gamemode change players");
    @EventHandler
    public void CommandEvent(PlayerCommandPreprocessEvent event) {
//Checks during the Player login Event!
        Player player = event.getPlayer();
        if (getConfig().getBoolean("Check during command preprocess")) {
            if (player.isOp()) {
                    if (!op.contains(player.getUniqueId().toString())) {
                        event.setCancelled(true);
                        player.setOp(false);
                        player.kickPlayer("You cannot be an operator!");
                        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + player.getName() + " wasn't allowed to be an Operator!");
                    }
                }
            }
        }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getConfig().getBoolean("Check on join")) {
            if (player.isOp()) {
                if (!op.contains(player.getUniqueId().toString())) {
                        player.setOp(false);
                        player.kickPlayer("You cannot be an operator!");
                        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + player.getName() + " wasn't allowed to be an Operator");
                }
            }
        }
    }
    @EventHandler
    public void GamemodeChangeEvent(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
       if (getConfig().getBoolean("Check gamemode change event")){
            if (!gm.contains(player.getUniqueId().toString())){
                event.setCancelled(true);
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED+player.getName()+" tried to change gamemode!");
            }
       }



    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//Checks during the process of the command!
        if (command.getName().equalsIgnoreCase("opreload")) {
            if (sender.hasPermission("AntiforceOP.reload") || sender.isOp()) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Config.yml Reloaded!");
                reloadConfig();
            } else {
                sender.sendMessage(ChatColor.RED + "Error: No permission to run this command");
            }
        }
        return true;
    }
}
