package com.tinyshellzz.spawnpointprotect.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {
    private final JavaPlugin plugin;

    public CommandTabCompleter(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            // 如果玩家只输入了命令的第一个参数，返回 "reload" 和 "add"
            if ("spp".equalsIgnoreCase(command.getName())) {
                if (args[0].isEmpty() || "reload".startsWith(args[0].toLowerCase())) {
                    completions.add("reload");
                }
                if (args[0].isEmpty() || "add".startsWith(args[0].toLowerCase())) {
                    completions.add("add");
                }
            }
        } else if (args.length == 2 && "add".equalsIgnoreCase(args[0])) {
            // 如果玩家输入了 "add"，返回在线玩家列表
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(player.getName());
                }
            }
        }
        return completions;
    }
}
