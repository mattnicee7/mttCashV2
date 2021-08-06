package me.matt.mttcashv2;

import me.matt.mttcashv2.commands.CashCommand;
import me.matt.mttcashv2.database.datasource.impl.MySQL;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.listener.PlayerJoin;
import me.matt.mttcashv2.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class Main extends JavaPlugin {

    private static Optional<Main> instance;

    ConsoleCommandSender cs = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        instance = Optional.of(this);
        loadConfigs();
        openConnection();

        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        closeConnection();
    }

    public void registerCommands() {
        new CashCommand(this);
    }

    public void registerEvents() { new PlayerJoin(this); }

    public void openConnection() {
        new DatabaseManager();
    }

    public void closeConnection() {
        DatabaseManager.getInstance().getDataSource().closeConnection();



        cs.sendMessage("§e[mttCash] Conexão com  fechada com sucesso.");
    }

    public void loadConfigs() {
        saveDefaultConfig();
        reloadConfig();

        MessageManager.messages.saveDefaultConfig();
        MessageManager.messages.reloadConfig();

        MessageManager.loadSimpleMessages();
        MessageManager.loadMultiMessages();
    }

    public static Main getInstance() {
        return instance.orElseThrow(() -> new IllegalStateException("Main instance is null"));
    }
}
