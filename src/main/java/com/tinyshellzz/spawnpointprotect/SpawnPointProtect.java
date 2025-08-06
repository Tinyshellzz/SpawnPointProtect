package com.tinyshellzz.spawnpointprotect;

import com.tinyshellzz.spawnpointprotect.command.CommandTabCompleter;
import com.tinyshellzz.spawnpointprotect.config.Config;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockBreakListener;
import com.tinyshellzz.spawnpointprotect.lisenter.BlockPlaceListener;
import com.tinyshellzz.spawnpointprotect.command.CommandExecutor;
import com.tinyshellzz.spawnpointprotect.lisenter.CreatureSpawnListener;
import com.tinyshellzz.spawnpointprotect.lisenter.NoFireSpreadListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        this.getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
        this.getServer().getPluginManager().registerEvents(new NoFireSpreadListener(), this);

        // 注册 spp 命令
        getCommand("spawnpointprotect").setExecutor(new CommandExecutor(this));
        getCommand("spp").setExecutor(new CommandExecutor(this));

        // 注册命令补全器
        Objects.requireNonNull(getCommand("spp")).setTabCompleter(new CommandTabCompleter(this));
    }
    }

