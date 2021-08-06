package me.matt.mttcashv2.manager;

import me.matt.mttcashv2.util.Config;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageManager {

    public static Config messages = new Config("messages.yml");

    public static Map<String, String> simpleMessages = new HashMap<>();
    public static Map<String, List<String>> multiMessages = new HashMap<>();

    public static void loadSimpleMessages() {
        simpleMessages.clear();
        simpleMessages.put("NoConsole", messages.getString("NoConsole"));
        simpleMessages.put("Offline", messages.getString("Offline"));
        simpleMessages.put("IncorrectSendSubCommand", messages.getString("IncorrectSendSubCommand"));
        simpleMessages.put("IncorrectSetSubCommand", messages.getString("IncorrectSetSubCommand"));
        simpleMessages.put("CashSee", messages.getString("CashSee"));
        simpleMessages.put("CashSeeOther", messages.getString("CashSeeOther"));
        simpleMessages.put("IsNotValidNumber", messages.getString("IsNotValidNumber"));
        simpleMessages.put("YouDontHaveCash", messages.getString("YouDontHaveCash"));
        simpleMessages.put("Sended", messages.getString("Sended"));
        simpleMessages.put("Received", messages.getString("Received"));
        simpleMessages.put("SettedWithSuccess", messages.getString("SettedWithSuccess"));
        simpleMessages.put("CashToYou", messages.getString("CashToYou"));
        simpleMessages.put("IncorrectGiveSubCommand", messages.getString("IncorrectGiveSubCommand"));
        simpleMessages.put("YouGived", messages.getString("YouGived"));
        simpleMessages.put("YouReceived", messages.getString("YouReceived"));
        simpleMessages.put("Removed", messages.getString("Removed"));
        simpleMessages.put("TargetDontHave", messages.getString("TargetDontHave"));
    }

    public static void loadMultiMessages() {
        multiMessages.clear();
        multiMessages.put("HelpCommand", messages.getStringList("HelpCommand"));
        multiMessages.put("HelpAdminCommand", messages.getStringList("HelpAdminCommand"));
    }

    public static String getSimpleMessage(String path) {
        return formatMessage(simpleMessages.get(path));
    }

    public static List<String> getMultiMessage(String path) {
        return formatMessage(multiMessages.get(path));
    }

    public static String formatMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> formatMessage(List<String> messages) {
        return messages.stream().map(it -> ChatColor.translateAlternateColorCodes('&', it)).collect(Collectors.toList());
    }
}
