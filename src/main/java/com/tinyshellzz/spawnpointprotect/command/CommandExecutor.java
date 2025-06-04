package com.tinyshellzz.spawnpointprotect.command;

import com.tinyshellzz.spawnpointprotect.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    private final JavaPlugin plugin;

    public CommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Config.reload();
            sender.sendMessage(ChatColor.GREEN + "【spp】配置文件已重新加载！");
            return true;
        }else if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            Player player = Bukkit.getPlayer(args[1]);
            UUID uuid;
            if (player == null) {
                //不在线，尝试从麻将官网获取
                uuid = getOfflinePlayerUUID(args[1]);
                if (uuid == null) {
                    sender.sendMessage(ChatColor.RED + "无法找到玩家的UUID。");
                    return true;
                }
            }else uuid = player.getUniqueId();//在线的话直接获取uuid
            if (Config.isPlayerWhitelisted(uuid)){
                sender.sendMessage(ChatColor.RED + "【spp】哥们他已经在白名单中了。");
                return true;
            }
            Config.whitelist.put(player.getName(), uuid);
            Config.saveWhitelist();
            sender.sendMessage(ChatColor.GREEN + "【spp】玩家 " + player.getName() + " 已添加到spp白名单！");
            return true;
        }
        return false;
    }

    private UUID getOfflinePlayerUUID(String playerName) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream()));
            String response = scanner.useDelimiter("\\A").next();
            scanner.close();

            // 解析响应以获取UUID
            String uuidString = response.substring(response.indexOf("\"id\":\"") + 6, response.indexOf("\"", response.indexOf("\"id\":\"") + 6));
            return UUID.fromString(uuidString.replaceFirst(
                    "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
                    "$1-$2-$3-$4-$5"
            ));
        } catch (Exception e) {
            Bukkit.getLogger().severe("获取失败: " + e.getMessage());
            return null;
        }
    }

}
