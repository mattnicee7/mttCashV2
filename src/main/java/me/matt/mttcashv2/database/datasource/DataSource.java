package me.matt.mttcashv2.database.datasource;

import java.sql.Connection;

public interface DataSource {

    Connection getConnection();
    void closeConnection();

}
