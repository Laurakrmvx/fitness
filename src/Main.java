import java.util.*;

public class Main {
    public static void main(String[] args) {
        User user1 = new StandardUser("John", 28, 75.0);
        User user2 = new PremiumUser("Jane", 32, 68.0, 5);
        User user3 = new StandardUser("Mike", 24, 80.5);

        WorkoutRoutine routine1 = new WorkoutRoutine("Yoga", 30);
        WorkoutRoutine routine2 = new WorkoutRoutine("HIIT", 20);
        WorkoutRoutine routine3 = new WorkoutRoutine("Cardio", 40);

        FitnessApp app = new FitnessApp();
        app.addUser(user1);
        app.addUser(user2);
        app.addUser(user3);

        User foundUser = app.findUserByName("Jane");
        System.out.println(foundUser != null ? "User Found By Name: " + foundUser : "User Not Found.");

        List<User> sortedUsers = app.getUsersSortedByAge();
        System.out.println("Users, sorted by age:");
        sortedUsers.forEach(System.out::println);

        List<User> filteredUsers = app.filterUsersByWeight(70);
        System.out.println("Users that are filtered by weight > 70:");
        filteredUsers.forEach(System.out::println);
    }
}

