package com.tinyshellzz.spawnpointprotect.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import java.util.*;

import static com.tinyshellzz.spawnpointprotect.ObjectPool.plugin;

public class Config {
    public static Location loc_a;
    public static Location loc_b;
    public static Location nether_loc_a;
    public static Location nether_loc_b;
    public static String Message_world;
    public static String Message_world_nether;
    public static HashMap<String, UUID> whitelist = new HashMap<>();

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

        // 从配置文件中读取白名单
        loadWhitelist();
    }

    private static Location getLocationFromConfig(String path) {
        double x = plugin.getConfig().getDouble(path + ".x");
        double y = plugin.getConfig().getDouble(path + ".y");
        double z = plugin.getConfig().getDouble(path + ".z");
        String worldName = plugin.getConfig().getString(path + ".world");

        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    // 从配置文件中加载白名单
    public static void loadWhitelist() {
        whitelist.clear();
        ConfigurationSection whitelistSection = plugin.getConfig().getConfigurationSection("whitelist");
        if (whitelistSection != null) {
            for (String key : whitelistSection.getKeys(false)) {
                String uuid_string = whitelistSection.getString(key);
                if (uuid_string != null) {
                    whitelist.put(key, UUID.fromString(uuid_string));
                }
            }
        }
    }

    // 将白名单保存到配置文件
    public static void saveWhitelist() {
        for (Map.Entry<String, UUID> entry : whitelist.entrySet()) {
            plugin.getConfig().set("whitelist." + entry.getKey(), entry.getValue().toString());
        }
        plugin.saveConfig();
    }

    // 检查玩家是否在白名单中
    public static boolean isPlayerWhitelisted(UUID uuid) {
        return whitelist.containsValue(uuid);
    }
}

