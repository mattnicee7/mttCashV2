package me.matt.mttcashv2.listener;

import lombok.val;
import me.matt.mttcashv2.Main;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.model.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Main main;

    public PlayerJoin(Main main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        val playerName = event.getPlayer().getName().toLowerCase();


        if (!DatabaseManager.getInstance().getUsers().has(playerName)) {
            DatabaseManager.getInstance().getUsers().insert(new User(playerName, 100.0));
        }
    }
}
