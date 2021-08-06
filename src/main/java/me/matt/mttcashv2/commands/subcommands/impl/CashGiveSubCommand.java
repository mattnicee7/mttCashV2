package me.matt.mttcashv2.commands.subcommands.impl;

import me.matt.mttcashv2.commands.subcommands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CashGiveSubCommand implements SubCommand {
    @Override
    public List<String> getAliases() {
        return Arrays.asList("give", "add");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        return false;
    }
}
