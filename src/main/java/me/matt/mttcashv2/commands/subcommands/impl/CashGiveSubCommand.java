package me.matt.mttcashv2.commands.subcommands.impl;

import lombok.val;
import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.manager.MessageManager;
import me.matt.mttcashv2.model.User;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CashGiveSubCommand implements SubCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("give", "add");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length != 3) {
            sender.sendMessage(MessageManager.getSimpleMessage("IncorrectGiveSubCommand"));
            return false;
        }

        val targetName = args[1].toLowerCase();

        if (DatabaseManager.getInstance().getUsers().getUser(targetName) == null) {
            sender.sendMessage(MessageManager.getSimpleMessage("Offline"));
            return false;
        }

        val targetUser = DatabaseManager.getInstance().getUsers().getUser(targetName);

        if (!StringUtils.isNumeric(args[2])) {
            sender.sendMessage(MessageManager.getSimpleMessage("IsNotValidNumber"));
            return false;
        }

        val amount = Double.parseDouble(args[2]);

        if (amount <= 0) {
            sender.sendMessage(MessageManager.getSimpleMessage("IsNotValidNumber"));
            return false;
        }

        val newTargetBalance = targetUser.getBalance() + amount;

        DatabaseManager.getInstance().getUsers().update(new User(targetName, newTargetBalance));
        sender.sendMessage(MessageManager.getSimpleMessage("YouGived")
                .replace("{cash}", String.format("%.2f", amount))
                .replace("{player}", args[1]));

        val target = Bukkit.getPlayer(args[1]);

        if (target == null) return false;

        if (sender instanceof ConsoleCommandSender) {
            target.sendMessage(MessageManager.getSimpleMessage("YouReceived")
                    .replace("{cash}", String.format("%.2f", amount))
                    .replace("{player}", "CONSOLE"));
        } else {
            val player = (Player) sender;

            target.sendMessage(MessageManager.getSimpleMessage("YouReceived")
            .replace("{cash}", String.format("%.2f", amount)
            .replace("{player}", player.getName())));
        }

        return false;
    }
}
