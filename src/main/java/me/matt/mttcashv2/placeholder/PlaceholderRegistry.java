package me.matt.mttcashv2.placeholder;

import me.matt.mttcashv2.placeholder.placeholders.CashBalancePlaceHolder;
import org.bukkit.Bukkit;

public class PlaceholderRegistry {

    public void register() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage("§e[mttCash] PlaceholderAPI não encontrado.");
        } else {
            new CashBalancePlaceHolder().register();
            Bukkit.getConsoleSender().sendMessage("§e[mttCash] Placeholders registradas com sucesso.");
        }
    }

}
