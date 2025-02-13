package main.model;

import java.time.LocalDate;
import java.util.Objects;

public class Workout {
    private int id;
    private int userId;
    private String type;
    private int duration; // in minutes
    private int caloriesBurned;
    private LocalDate date;

    public Workout(int id, int userId, String type, int duration, int caloriesBurned, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    // Overriding equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Workout)) return false;
        Workout workout = (Workout) obj;
        return id == workout.id && userId == workout.userId &&
                duration == workout.duration && caloriesBurned == workout.caloriesBurned &&
                Objects.equals(type, workout.type) && Objects.equals(date, workout.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, type, duration, caloriesBurned, date);
    }

    @Override
    public String toString() {
        return "Workout{id=" + id + ", userId=" + userId + ", type='" + type + "', duration=" +
                duration + ", caloriesBurned=" + caloriesBurned + ", date=" + date + "}";
    }
}
