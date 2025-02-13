package main.service;

import main.model.User;
import main.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void registerUser(User user) throws SQLException {
        userRepository.addUser(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public void updateUser(User user) throws SQLException {
        userRepository.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userRepository.deleteUser(userId);
    }
}
