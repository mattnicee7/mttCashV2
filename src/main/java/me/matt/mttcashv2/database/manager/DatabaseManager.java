package me.matt.mttcashv2.database.manager;

import lombok.Getter;
import lombok.val;
import me.matt.mttcashv2.Main;
import me.matt.mttcashv2.database.datasource.DataSource;
import me.matt.mttcashv2.database.datasource.impl.MySQL;
import me.matt.mttcashv2.database.datasource.impl.SQLite;
import me.matt.mttcashv2.database.datasource.table.Users;
import org.bukkit.Bukkit;

import java.util.Optional;

@Getter
public class DatabaseManager {

    private static Optional<DatabaseManager> instance;

    private DataSource dataSource;
    private Users users;

    public DatabaseManager() {
        val start = System.currentTimeMillis();

        users = new Users();

        val section = Main.getInstance().getConfig().getConfigurationSection("MySQL");

        val use= section.getBoolean("Use");

        if (!use) {
            Bukkit.getConsoleSender().sendMessage("§e[mttCash] MySQL Desabilitado. Habilitando SQLite...");
            dataSource = new SQLite();
        } else {

            val name = section.getString("Name");
            val host = section.getString("Host");
            val port = section.getInt("Port");
            val database = section.getString("Database");
            val password = section.getString("Password");

            dataSource = new MySQL(name, host, port, database, password);
            Bukkit.getConsoleSender().sendMessage("§e[mttCash] Banco de dados carregado em (" + (System.currentTimeMillis() - start) + "ms).");

        }
        instance = Optional.of(this);

        users.createTable();
    }

    public static DatabaseManager getInstance() {
        return instance.orElseThrow(() -> new IllegalStateException("DatabaseManager instance is null"));
    }


}
