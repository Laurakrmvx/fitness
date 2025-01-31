import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

class UserHandler {
    private final Connection connection;

    public UserHandler(Connection connection) {
        this.connection = connection;
    }

    public void addUser(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter weight (kg): ");
        double weight = scanner.nextDouble();

        System.out.print("Enter height (cm): ");
        double height = scanner.nextDouble();
        scanner.nextLine();

        String insertQuery = "INSERT INTO users (name, age, weight, height) VALUES (?, ?, ?, ?);";
        try (var statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setDouble(3, weight);
            statement.setDouble(4, height);
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewUsers() {
        String selectQuery = "SELECT * FROM users;";
        try (var statement = connection.prepareStatement(selectQuery);
             var resultSet = statement.executeQuery()) {

            System.out.println("\nRegistered Users:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                double height = resultSet.getDouble("height");
                System.out.printf("ID: %d, Name: %s, Age: %d, Weight: %.2f kg, Height: %.2f cm%n", id, name, age, weight, height);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
