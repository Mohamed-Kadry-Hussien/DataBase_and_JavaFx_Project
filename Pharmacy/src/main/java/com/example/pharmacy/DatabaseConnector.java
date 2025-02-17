package com.example.pharmacy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public DatabaseConnector() {
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://USER:1433;databaseName=Pharmacy_DB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;");
    }

    public boolean testConnection() {
        try {
            Connection connection = this.connect();

            boolean var2;
            try {
                System.out.println("Connection successful!");
                var2 = true;
            } catch (Throwable var5) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (connection != null) {
                connection.close();
            }

            return var2;
        } catch (SQLException var6) {
            SQLException e = var6;
            System.err.println("Connection failed: " + e.getMessage());
            return false;
        }
    }
}
