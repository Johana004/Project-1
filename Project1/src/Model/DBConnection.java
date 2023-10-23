package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* A class for managing the database connection.
 */
public class DBConnection {
    // Database connection parameters
    static String bd = "project1";
    static String port = "3306";
    static String login = "root";
    static String password = "Chavala";
    static String url = "jdbc:mariadb://localhost:" + port + "/" + bd;

    Connection connection = null;

    /* Constructor for the DBConnection class. It initializes the database connection.
     */
    public DBConnection() {
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Establish a connection to the database
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Successful database connection");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading the JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error establishing the connection: " + e.getMessage());
        }
    }

    /**
     * Get the established database connection.
     *
     * @return The database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Disconnect from the database. Closes the connection.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}
