package com.tinyshellzz.spwanpointprotect.lisenter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void handle(BlockBreakEvent event){
        Player p = event.getPlayer();
        Bukkit.getConsoleSender().sendMessage(p + " interacted");

        event.setCancelled(true);
    }
}
