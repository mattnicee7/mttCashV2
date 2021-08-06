package me.matt.mttcashv2.database.datasource.impl;

import lombok.val;
import me.matt.mttcashv2.Main;
import me.matt.mttcashv2.database.datasource.DataSource;
import me.matt.mttcashv2.model.User;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.*;

public class SQLite implements DataSource {

    private static Connection conn;

    public SQLite() {
        val file = new File(Main.getInstance().getDataFolder(), "database.db");
        val url = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            Bukkit.getConsoleSender().sendMessage("§e[mttCash] Conexao com SQLite aberta com sucesso.");
        } catch (SQLException | ClassNotFoundException exception) {
            conn = null;
            exception.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return conn;
    }

    @Override
    public void closeConnection() {
        try {
            if (getConnection() != null || !getConnection().isClosed()) {
                Bukkit.getConsoleSender().sendMessage("§e[mttCash] Conexao com SQLite fechada com sucesso.");
                getConnection().close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
