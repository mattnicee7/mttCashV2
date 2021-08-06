package me.matt.mttcashv2.database.datasource.table;

import lombok.NoArgsConstructor;
import lombok.val;
import me.matt.mttcashv2.database.manager.DatabaseManager;
import me.matt.mttcashv2.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class Users {

    public void createTable() {
        val conn = DatabaseManager.getInstance().getDataSource().getConnection();

        try (PreparedStatement st = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (" +
                "playerName VARCHAR(30) NOT NULL," +
                "balance DOUBLE NOT NULL)")) {
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void update(User user) {
        val conn = DatabaseManager.getInstance().getDataSource().getConnection();

        try (PreparedStatement st = conn.prepareStatement("UPDATE users SET balance = ? WHERE playerName = ?")) {
            st.setDouble(1, user.getBalance());
            st.setString(2, user.getName());

            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void insert(User user) {
        val conn = DatabaseManager.getInstance().getDataSource().getConnection();

        try (PreparedStatement st = conn.prepareStatement("INSERT INTO users (playerName, balance) VALUES (?, ?)")) {
            st.setString(1, user.getName());
            st.setDouble(2, user.getBalance());

            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean has(String playerName) {
        val conn = DatabaseManager.getInstance().getDataSource().getConnection();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE playerName = ?")) {
            st.setString(1, playerName);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return true;
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public User getUser(String playerName) {
        val conn = DatabaseManager.getInstance().getDataSource().getConnection();

        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM users WHERE playerName = ?")) {
            st.setString(1, playerName);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    val name = rs.getString("playerName");
                    val balance = rs.getDouble("balance");

                    return new User(name, balance);
                }

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
