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

import java.util.Arrays;
import java.util.List;

public class CashSetSubCommand implements SubCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("set", "setar");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length != 3) {
            sender.sendMessage(MessageManager.getSimpleMessage("IncorrectSetSubCommand"));
            return false;
        }

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

        DatabaseManager.getInstance().getUsers().update(new User(targetUser.getName(), amount));
        sender.sendMessage(MessageManager.getSimpleMessage("SettedWithSuccess")
                .replace("{player}", args[1])
                .replace("{cash}", String.format("%.2f", amount)));


        return false;
    }
}
