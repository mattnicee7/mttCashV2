package me.matt.mttcashv2.commands.subcommands.impl;

import lombok.val;
import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.manager.MessageManager;
import me.matt.mttcashv2.model.User;
import me.matt.mttcashv2.util.Methods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CashSendSubCommand implements SubCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("enviar", "send");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(MessageManager.getSimpleMessage("NoConsole"));
            return false;
        }

        if (args.length != 3) {
            sender.sendMessage(MessageManager.getSimpleMessage("IncorrectSendSubCommand"));
            return false;
        }

        val target = Bukkit.getPlayer(args[1]);
        val player = (Player) sender;

        if (player.equals(target)) {
            sender.sendMessage(MessageManager.getSimpleMessage("CashToYou"));
            return false;
        }

        val targetName = args[1].toLowerCase();

        if (DatabaseManager.getInstance().getUsers().getUser(targetName) == null) {
            sender.sendMessage(MessageManager.getSimpleMessage("Offline"));
            return false;
        } else {

            StringUtils.isNumeric("sim");

            if (!Methods.isNumeric(args[2])) {
                sender.sendMessage(MessageManager.getSimpleMessage("IsNotValidNumber"));
                return false;
            }

            val amount = Double.parseDouble(args[2]);

            if (amount <= 0) {
                sender.sendMessage("IsNotValidNumber");
                return false;
            }

            val playerUser = DatabaseManager.getInstance().getUsers().getUser(player.getName().toLowerCase());

            if (amount > playerUser.getBalance()) {
                player.sendMessage(MessageManager.getSimpleMessage("YouDontHaveCash"));
                return false;
            }

            val targetUser = DatabaseManager.getInstance().getUsers().getUser(targetName);

            val playerNewBalance = playerUser.getBalance() - amount;
            val targetNewBalance = targetUser.getBalance() + amount;

            DatabaseManager.getInstance().getUsers().update(new User(playerUser.getName().toLowerCase(), playerNewBalance));
            DatabaseManager.getInstance().getUsers().update(new User(targetUser.getName().toLowerCase(), targetNewBalance));

            player.sendMessage(MessageManager.getSimpleMessage("Sended")
                    .replace("{cash}", String.format("%.2f", amount))
                    .replace("{player}", args[1]));


            if (target != null) {
                target.sendMessage(MessageManager.getSimpleMessage("Received")
                        .replace("{cash}", String.format("%.2f", amount))
                        .replace("{player}", player.getName()));
            }

        }
        return false;
    }
}
