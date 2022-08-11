package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This abstract class is the class that establishes, provides, and closes the connection with the database.
 *
 * @author Ashley Jensen
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // Local
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    private static Connection connection; // Connection Interface

    /**
     * This method opens the connection to the database, else alerts error.
     */
    public static void openConnection() {
            try {
                Class.forName(driver); // Local Driver
                connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    /**
     * This method returns a connection to the database when needed.
     * @return Connection connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * This method closes the connection to the database, else alerts error.
     */
    public static void closeConnection() {
        try {
            connection.close();

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
