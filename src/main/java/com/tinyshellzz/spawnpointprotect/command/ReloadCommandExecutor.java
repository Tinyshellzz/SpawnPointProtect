package com.tinyshellzz.spawnpointprotect.command;

import com.tinyshellzz.spawnpointprotect.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommandExecutor implements CommandExecutor {
    private final JavaPlugin plugin;

    public ReloadCommandExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            Config.Message_world = plugin.getConfig().getString("message_world"); // 更新自定义消息
            Config.Message_world_nether = plugin.getConfig().getString("message_world_nether"); // 更新自定义消息
            sender.sendMessage(ChatColor.GREEN + "【spp】配置文件已重新加载！");
            return true;
        }
        return false;
    }
}
