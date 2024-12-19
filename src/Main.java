import java.util.*;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("John", 28, 75.0);
        User user2 = new User("Jane", 32, 68.0);

        WorkoutRoutine routine1 = new WorkoutRoutine("Yoga", 30);
        WorkoutRoutine routine2 = new WorkoutRoutine("HIIT", 20);

        FitnessApp app = new FitnessApp();
        app.addUser(user1);
        app.addUser(user2);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(routine1);
        System.out.println(routine2);
        System.out.println(user1.getName() + " is " + (user1.getAge() > user2.getAge() ? "older" : "younger") + " than " + user2.getName());
    }
}

class User {
    private String name;
    private int age;
    private double weight;

    public User(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", weight=" + weight + "}";
    }
}

class WorkoutRoutine {
    private String name;
    private int duration;

    public WorkoutRoutine(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "WorkoutRoutine{name='" + name + "', duration=" + duration + "}";
    }
}

class FitnessApp {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public String toString() {
        return "FitnessApp{users=" + users + "}";
    }
}

