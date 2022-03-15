package tec.dk.demo1.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {
    private String connectionString = "jdbc:mysql://localhost/persondb";
    private String userName = "persondbuser";
    private String userPassword = "Kage1234";
    private Connection connection;

    public MariaDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionString, userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}