package com.tinyshellzz.spawnpointprotect.lisenter;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.utils.Tools;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Location loc = event.getLocation();
        EntityType type = event.getEntityType();
        switch (type) {
            case VINDICATOR, PILLAGER, RAVAGER, EVOKER:
                if (loc.getWorld().getName().equals("world")) {
                    if (Tools.inArea(loc, Config.loc_a, Config.loc_b)) {
                        event.setCancelled(true);
                    }
                }
                break;
        }
    }
}
