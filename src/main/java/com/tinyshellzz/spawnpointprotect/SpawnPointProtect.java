package com.tinyshellzz.spawnpointprotect;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockBreakListener;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockPlaceListener;
import com.tinyshellzz.spawnpointprotect.command.ReloadCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPointProtect extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("+++++++++++++++++++++++++++++");
        init(); // 加载配置文件
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init() {
        ObjectPool.plugin = this;
        Config.reload();
    }


    public void register() {
        // 注册 Listeners
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        // 注册 reload 命令
        getCommand("spawnpointprotect").setExecutor(new ReloadCommandExecutor(this));
        getCommand("spp").setExecutor(new ReloadCommandExecutor(this));
    }
}
