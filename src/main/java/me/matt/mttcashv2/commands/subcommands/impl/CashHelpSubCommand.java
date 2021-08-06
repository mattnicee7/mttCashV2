package me.matt.mttcashv2.commands.subcommands.impl;

import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.manager.MessageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CashHelpSubCommand implements SubCommand {

    @Override
    public List<String> getAliases() {
        return Arrays.asList("help", "ajuda", "?");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (!(sender instanceof Player) || sender.hasPermission("mttcash.admin") || sender.isOp()) {
            MessageManager.getMultiMessage("HelpAdminCommand").forEach(sender::sendMessage);
        } else {
            MessageManager.getMultiMessage("HelpCommand").forEach(sender::sendMessage);
        }

        return false;
    }
}
