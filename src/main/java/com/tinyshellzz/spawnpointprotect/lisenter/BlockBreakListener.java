package com.tinyshellzz.spawnpointprotect.lisenter;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.utils.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void handle(BlockBreakEvent event){
        Player p = event.getPlayer();
        Bukkit.getConsoleSender().sendMessage(p.getStatistic(Statistic.PLAY_ONE_MINUTE) + "");
        int playTime = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000; // 获取玩家的总游玩时长，单位为小时
        if (playTime > 48) return;     // 游玩时间大于48小时，则不保护

        Location loc = p.getLocation();
        if (Tools.inArea(loc, Config.loc_a, Config.loc_b)) {
            event.setCancelled(true);
        }
    }
}
