import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_reservation";
    private static final String USER = System.getenv("DB_USER");  //Check README.md file
    private static final String PASSWORD = System.getenv("DB_PASSWORD");  //Check README.md file

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database connection failed", e);
        }
    }
}
