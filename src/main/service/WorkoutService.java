package main.service;

import main.model.Workout;
import main.repository.WorkoutRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutService {
    private final WorkoutRepository workoutRepository = new WorkoutRepository();

    public void addWorkout(Workout workout) throws SQLException {
        workoutRepository.addWorkout(workout);
    }

    public List<Workout> getAllWorkouts() throws SQLException {
        return workoutRepository.getAllWorkouts();
    }

    public List<Workout> filterWorkoutsByType(String type) throws SQLException {
        return workoutRepository.getAllWorkouts()
                .stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Workout> sortWorkoutsByDuration() throws SQLException {
        return workoutRepository.getAllWorkouts()
                .stream()
                .sorted((w1, w2) -> Integer.compare(w1.getDuration(), w2.getDuration()))
                .collect(Collectors.toList());
    }

    public void updateWorkout(Workout workout) throws SQLException {
        workoutRepository.updateWorkout(workout);
    }

    public void deleteWorkout(int workoutId) throws SQLException {
        workoutRepository.deleteWorkout(workoutId);
    }
}
