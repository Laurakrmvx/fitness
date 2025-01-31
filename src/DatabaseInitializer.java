import java.sql.Connection;
import java.sql.SQLException;

class DatabaseInitializer {
    public static void initializeDatabase(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT NOT NULL," +
                "weight DECIMAL(5, 2) NOT NULL," +
                "height DECIMAL(5, 2) NOT NULL" +
                ");";
        try (var statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
        }
    }
}
