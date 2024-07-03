package com.tinyshellzz.spawnpointprotect;

import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockBreakListener;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockPlaceListener;
import com.tinyshellzz.spawnpointprotect.utils.ReloadCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnPointProtect extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("+++++++++++++++++++++++++++++");
        loadConfig(); // 加载配置文件
        register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        saveDefaultConfig(); // 如果配置文件不存在，创建默认的 config.yml
        reloadConfig(); // 重新加载配置文件

        // 从配置文件中读取坐标点位
        Config.loc_a = getLocationFromConfig("zhushijie.a");
        Config.loc_b = getLocationFromConfig("zhushijie.b");
        Config.nether_loc_a = getLocationFromConfig("xiajie.a");
        Config.nether_loc_b = getLocationFromConfig("xiajie.b");
    }

    private Location getLocationFromConfig(String path) {
        double x = getConfig().getDouble(path + ".x");
        double y = getConfig().getDouble(path + ".y");
        double z = getConfig().getDouble(path + ".z");
        String worldName = getConfig().getString(path + ".world");

        return new Location(Bukkit.getWorld(worldName), x, y, z);
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
