package me.matt.mttcashv2.commands.subcommands.impl;

import lombok.val;
import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CashSeeSubCommand implements SubCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("see", "ver");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length == 1 && sender instanceof Player) {
            val player = (Player) sender;
            val playerUser = DatabaseManager.getInstance().getUsers().getUser(player.getName().toLowerCase());

            player.sendMessage(MessageManager.getSimpleMessage("CashSee")
                    .replace("{cash}", String.format("%.2f", playerUser.getBalance())));
        }

        if (args.length == 2) {
            val targetName = args[1].toLowerCase();

            if (DatabaseManager.getInstance().getUsers().getUser(targetName) != null) {

                val targetUser = DatabaseManager.getInstance().getUsers().getUser(targetName);

                sender.sendMessage(MessageManager.getSimpleMessage("CashSeeOther")
                        .replace("{player}", args[1])
                        .replace("{cash}", String.format("%.2f", targetUser.getBalance())));
            } else {
                sender.sendMessage(MessageManager.getSimpleMessage("Offline"));
            }

        }

        return false;
    }
}
