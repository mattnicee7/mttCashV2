package me.matt.mttcashv2.commands.subcommands.impl;

import lombok.val;
import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.manager.MessageManager;
import me.matt.mttcashv2.model.User;
import me.matt.mttcashv2.util.Methods;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CashRemoveSubCommand implements SubCommand {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("remove", "remover");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        val targetName = args[1].toLowerCase();

        if (DatabaseManager.getInstance().getUsers().getUser(targetName) == null) {
            sender.sendMessage(MessageManager.getSimpleMessage("Offline"));
            return false;
        }

        if (!Methods.isNumeric(args[2])) {
            sender.sendMessage(MessageManager.getSimpleMessage("IsNotValidNumber"));
            return false;
        }

        val amount = Double.parseDouble(args[2]);

        if (amount <= 0) {
            sender.sendMessage(MessageManager.getSimpleMessage("IsNotValidNumber"));
            return false;
        }

        val targetUser = DatabaseManager.getInstance().getUsers().getUser(targetName);

        if (amount > targetUser.getBalance()) {
            sender.sendMessage(MessageManager.getSimpleMessage("TargetDontHave"));
            return false;
        }

        val targetNewBalance = targetUser.getBalance() - amount;

        DatabaseManager.getInstance().getUsers().update(new User(targetName, targetNewBalance));

        sender.sendMessage(MessageManager.getSimpleMessage("Removed")
        .replace("{cash}", String.format("%.2f", amount))
        .replace("{player}", args[1]));

        return false;
    }
}
