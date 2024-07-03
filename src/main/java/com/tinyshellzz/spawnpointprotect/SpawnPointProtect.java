package com.tinyshellzz.spwanpointprotect;

import org.bukkit.Bukkit;
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
        ObjectPool.silenceSender = new SilenceConsoleCommandSender(Bukkit.getConsoleSender());
    }
    public void register(){
        // 注册 Listeners
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

    }
}
