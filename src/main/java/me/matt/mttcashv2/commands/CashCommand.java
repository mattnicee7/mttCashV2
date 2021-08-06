package me.matt.mttcashv2.commands;

import lombok.val;
import me.matt.mttcashv2.Main;
import me.matt.mttcashv2.commands.subcommands.SubCommand;
import me.matt.mttcashv2.commands.subcommands.impl.*;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CashCommand implements CommandExecutor {

    private final Main main;
    private List<SubCommand> subCommands = new ArrayList<>();

    public CashCommand(Main main) {
        this.main = main;
        subCommands.add(new CashHelpSubCommand());
        subCommands.add(new CashSendSubCommand());
        subCommands.add(new CashGiveSubCommand());
        subCommands.add(new CashSetSubCommand());
        subCommands.add(new CashSeeSubCommand());
        main.getCommand("cash").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && args.length == 0) Bukkit.dispatchCommand(sender, "cash see");


        if (args.length >= 1) {

            val target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                val targetUser = DatabaseManager.getInstance().getUsers().getUser(target.getName());
                sender.sendMessage(MessageManager.getSimpleMessage("CashSeeOther")
                .replace("{player}", target.getName())
                .replace("{cash}", String.format("%.2f", targetUser.getBalance())));
            }

            for (SubCommand subCommand : subCommands) {
                if (subCommand.getAliases().contains(args[0].toLowerCase())) {
                    return subCommand.execute(sender, args);
                }
            }

        }

        Bukkit.dispatchCommand(sender, "cash help");

        return false;
    }
}
