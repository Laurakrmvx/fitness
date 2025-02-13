package main.repository;

import main.config.DatabaseConnection;
import main.model.Workout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRepository {
    public void addWorkout(Workout workout) throws SQLException {
        String sql = "INSERT INTO workouts (user_id, type, duration, calories_burned, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, workout.getUserId());
            stmt.setString(2, workout.getType());
            stmt.setInt(3, workout.getDuration());
            stmt.setInt(4, workout.getCaloriesBurned());
            stmt.setDate(5, Date.valueOf(workout.getDate()));
            stmt.executeUpdate();
        }
    }

    public List<Workout> getAllWorkouts() throws SQLException {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workouts";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                workouts.add(new Workout(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getString("type"), rs.getInt("duration"),
                        rs.getInt("calories_burned"), rs.getDate("date").toLocalDate()));
            }
        }
        return workouts;
    }

    public void updateWorkout(Workout workout) throws SQLException {
        String sql = "UPDATE workouts SET type=?, duration=?, calories_burned=?, date=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workout.getType());
            stmt.setInt(2, workout.getDuration());
            stmt.setInt(3, workout.getCaloriesBurned());
            stmt.setDate(4, Date.valueOf(workout.getDate()));
            stmt.setInt(5, workout.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteWorkout(int workoutId) throws SQLException {
        String sql = "DELETE FROM workouts WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, workoutId);
            stmt.executeUpdate();
        }
    }
}
