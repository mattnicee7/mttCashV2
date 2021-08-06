package me.matt.mttcashv2.database.datasource.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import me.matt.mttcashv2.database.datasource.DataSource;
import me.matt.mttcashv2.model.User;
import org.bukkit.Bukkit;

import java.sql.*;

@Getter
@Setter
public class MySQL implements DataSource {

    private Connection conn;

    public MySQL(String name, String host, int port, String database, String password) {

        val url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, name, password);
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
                getConnection().close();
                Bukkit.getConsoleSender().sendMessage("§e[mttCash] Conexão com MySQL fechada com sucesso.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
