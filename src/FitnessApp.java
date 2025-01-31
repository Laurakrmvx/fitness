import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Main class responsible for establishing the database connection and launching the application.
public class FitnessApp {

    private static final String URL = "jdbc:postgresql://localhost:5432/fitnessdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the PostgreSQL database successfully.");
            // Initialize the database table if it does not exist.
            DatabaseInitializer.initializeDatabase(connection);
            // Launch the main menu for user interaction.
            new MenuHandler(connection).showMainMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

