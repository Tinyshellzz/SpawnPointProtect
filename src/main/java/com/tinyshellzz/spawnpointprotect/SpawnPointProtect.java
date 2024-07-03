package com.tinyshellzz.spawnpointprotect;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockBreakListener;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPointProtect extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("+++++++++++++++++++++++++++++");
        init();
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init() {
        ObjectPool.plugin = this;
        Config.loc_a = new Location(Bukkit.getServer().getWorld("World"), 500, 300, 500);
        Config.loc_b = new Location(Bukkit.getServer().getWorld("World"), -500, 0, -500);
    }
    public void register(){
        // 注册 Listeners
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
    }
}
