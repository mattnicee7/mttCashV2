package me.matt.mttcashv2.commands.subcommands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {

    List<String> getAliases();
    boolean execute(CommandSender sender, String[] args);

}
