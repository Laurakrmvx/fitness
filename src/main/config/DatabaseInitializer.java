package main.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "age INT NOT NULL," +
                    "weight DOUBLE PRECISION NOT NULL," +
                    "height DOUBLE PRECISION NOT NULL" +
                    ");";

            String createWorkoutsTable = "CREATE TABLE IF NOT EXISTS workouts (" +
                    "id SERIAL PRIMARY KEY," +
                    "user_id INT REFERENCES users(id) ON DELETE CASCADE," +
                    "type VARCHAR(50) NOT NULL," +
                    "duration INT NOT NULL," +
                    "calories_burned INT NOT NULL," +
                    "date DATE NOT NULL" +
                    ");";

            stmt.executeUpdate(createUsersTable);
            stmt.executeUpdate(createWorkoutsTable);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }
}
