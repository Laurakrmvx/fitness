import java.sql.Connection;
import java.util.Scanner;

class MenuHandler {
    private final Connection connection;
    private final UserHandler userHandler;

    public MenuHandler(Connection connection) {
        this.connection = connection;
        this.userHandler = new UserHandler(connection);
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> userHandler.addUser(scanner);
                case 2 -> userHandler.viewUsers();
                case 3 -> {
                    System.out.println("Exiting the application.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
