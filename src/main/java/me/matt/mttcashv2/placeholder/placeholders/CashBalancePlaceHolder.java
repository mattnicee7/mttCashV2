package me.matt.mttcashv2.placeholder.placeholders;

import lombok.val;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.matt.mttcashv2.Main;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CashBalancePlaceHolder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "mttcashv2";
    }

    @Override
    public @NotNull String getAuthor() {
        return "mattnicee7";
    }

    @Override
    public @NotNull String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player != null && params.equalsIgnoreCase("balance") ) {
            val playerAccount = DatabaseManager.getInstance().getUsers().getUser(player.getName().toLowerCase());
            val playerBalance = playerAccount.getBalance();

            return String.format("%.2f", playerBalance);
        }

        return "";
    }
}
