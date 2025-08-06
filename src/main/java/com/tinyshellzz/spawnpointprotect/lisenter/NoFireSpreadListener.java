package com.tinyshellzz.spawnpointprotect.lisenter;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.utils.Tools;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.Listener;

/**
 * 取消火焰蔓延
 */
public class NoFireSpreadListener implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        Location loc = event.getBlock().getLocation();

        // Check if the fire is spreading naturally
        if (event.getCause() == BlockIgniteEvent.IgniteCause.SPREAD) {
            if (loc.getWorld().getName().equals("world")) {
                if (Tools.inArea(loc, Config.loc_a, Config.loc_b)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
