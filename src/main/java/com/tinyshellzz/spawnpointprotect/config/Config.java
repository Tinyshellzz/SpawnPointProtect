package com.tinyshellzz.spawnpointprotect.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import static com.tinyshellzz.spawnpointprotect.ObjectPool.plugin;

public class Config {
    public static Location loc_a;
    public static Location loc_b;
    public static Location nether_loc_a;
    public static Location nether_loc_b;
    public static String Message_world;
    public static String Message_world_nether;

    public static void reload() {
        plugin.saveDefaultConfig(); // 如果配置文件不存在，创建默认的 config.yml
        plugin.reloadConfig(); // 重新加载配置文件

        // 从配置文件中读取坐标点位
        Config.loc_a = getLocationFromConfig("world.a");
        Config.loc_b = getLocationFromConfig("world.b");
        Config.nether_loc_a = getLocationFromConfig("world_nether.a");
        Config.nether_loc_b = getLocationFromConfig("world_nether.b");

        // 从配置文件中读取自定义消息
        Config.Message_world = plugin.getConfig().getString("message_world");
        Config.Message_world_nether = plugin.getConfig().getString("message_world_nether");
    }

    private static Location getLocationFromConfig(String path) {
        double x = plugin.getConfig().getDouble(path + ".x");
        double y = plugin.getConfig().getDouble(path + ".y");
        double z = plugin.getConfig().getDouble(path + ".z");
        String worldName = plugin.getConfig().getString(path + ".world");

        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }
}
