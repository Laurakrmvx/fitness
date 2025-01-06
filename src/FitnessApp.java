import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class FitnessApp {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersSortedByAge() {
        List<User> sortedUsers = new ArrayList<>(users);
        sortedUsers.sort(Comparator.comparingInt(User::getAge));
        return sortedUsers;
    }

    public List<User> filterUsersByWeight(double minWeight) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getWeight() > minWeight) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    @Override
    public String toString() {
        return "FitnessApp{users=" + users + "}";
    }
}
