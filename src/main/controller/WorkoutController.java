package main.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.model.Workout;
import main.service.WorkoutService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class WorkoutController implements HttpHandler {
    private final WorkoutService workoutService = new WorkoutService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        try {
            switch (method) {
                case "GET" -> handleGetWorkouts(exchange);
                case "POST" -> handleCreateWorkout(exchange);
                case "PUT" -> handleUpdateWorkout(exchange);
                case "DELETE" -> handleDeleteWorkout(exchange);
                default -> sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (SQLException e) {
            sendResponse(exchange, 500, "Database Error: " + e.getMessage());
        }
    }

    private void handleGetWorkouts(HttpExchange exchange) throws IOException, SQLException {
        List<Workout> workouts = workoutService.getAllWorkouts();
        String response = workouts.toString();
        sendResponse(exchange, 200, response);
    }

    private void handleCreateWorkout(HttpExchange exchange) throws IOException, SQLException {
        Scanner scanner = new Scanner(exchange.getRequestBody());
        String requestBody = scanner.useDelimiter("\\A").next();
        scanner.close();

        String[] parts = requestBody.split(",");
        if (parts.length < 5) {
            sendResponse(exchange, 400, "Invalid input format.");
            return;
        }

        int userId = Integer.parseInt(parts[0].trim());
        String type = parts[1].trim();
        int duration = Integer.parseInt(parts[2].trim());
        int calories = Integer.parseInt(parts[3].trim());
        LocalDate date = LocalDate.parse(parts[4].trim());

        workoutService.addWorkout(new Workout(0, userId, type, duration, calories, date));
        sendResponse(exchange, 201, "Workout added successfully.");
    }

    private void handleUpdateWorkout(HttpExchange exchange) throws IOException, SQLException {
        Scanner scanner = new Scanner(exchange.getRequestBody());
        String requestBody = scanner.useDelimiter("\\A").next();
        scanner.close();

        String[] parts = requestBody.split(",");
        if (parts.length < 6) {
            sendResponse(exchange, 400, "Invalid input format.");
            return;
        }

        int id = Integer.parseInt(parts[0].trim());
        int userId = Integer.parseInt(parts[1].trim());
        String type = parts[2].trim();
        int duration = Integer.parseInt(parts[3].trim());
        int calories = Integer.parseInt(parts[4].trim());
        LocalDate date = LocalDate.parse(parts[5].trim());

        workoutService.updateWorkout(new Workout(id, userId, type, duration, calories, date));
        sendResponse(exchange, 200, "Workout updated successfully.");
    }

    private void handleDeleteWorkout(HttpExchange exchange) throws IOException, SQLException {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || !query.startsWith("id=")) {
            sendResponse(exchange, 400, "Missing workout ID.");
            return;
        }

        int workoutId = Integer.parseInt(query.split("=")[1]);
        workoutService.deleteWorkout(workoutId);
        sendResponse(exchange, 200, "Workout deleted successfully.");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
