package main.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.model.User;
import main.service.UserService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController implements HttpHandler {
    private final UserService userService = new UserService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        try {
            switch (method) {
                case "GET" -> handleGetUsers(exchange);
                case "POST" -> handleCreateUser(exchange);
                case "PUT" -> handleUpdateUser(exchange);
                case "DELETE" -> handleDeleteUser(exchange);
                default -> sendResponse(exchange, 405, "Method Not Allowed");
            }
        } catch (SQLException e) {
            sendResponse(exchange, 500, "Database Error: " + e.getMessage());
        }
    }

    private void handleGetUsers(HttpExchange exchange) throws IOException, SQLException {
        List<User> users = userService.getAllUsers();
        String response = users.toString();
        sendResponse(exchange, 200, response);
    }

    private void handleCreateUser(HttpExchange exchange) throws IOException, SQLException {
        Scanner scanner = new Scanner(exchange.getRequestBody());
        String requestBody = scanner.useDelimiter("\\A").next();
        scanner.close();

        String[] parts = requestBody.split(",");
        if (parts.length < 4) {
            sendResponse(exchange, 400, "Invalid input format.");
            return;
        }

        String name = parts[0].trim();
        int age = Integer.parseInt(parts[1].trim());
        double weight = Double.parseDouble(parts[2].trim());
        double height = Double.parseDouble(parts[3].trim());

        userService.registerUser(new User(0, name, age, weight, height));
        sendResponse(exchange, 201, "User created successfully.");
    }

    private void handleUpdateUser(HttpExchange exchange) throws IOException, SQLException {
        Scanner scanner = new Scanner(exchange.getRequestBody());
        String requestBody = scanner.useDelimiter("\\A").next();
        scanner.close();

        String[] parts = requestBody.split(",");
        if (parts.length < 5) {
            sendResponse(exchange, 400, "Invalid input format.");
            return;
        }

        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        double weight = Double.parseDouble(parts[3].trim());
        double height = Double.parseDouble(parts[4].trim());

        userService.updateUser(new User(id, name, age, weight, height));
        sendResponse(exchange, 200, "User updated successfully.");
    }

    private void handleDeleteUser(HttpExchange exchange) throws IOException, SQLException {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || !query.startsWith("id=")) {
            sendResponse(exchange, 400, "Missing user ID.");
            return;
        }

        int userId = Integer.parseInt(query.split("=")[1]);
        userService.deleteUser(userId);
        sendResponse(exchange, 200, "User deleted successfully.");
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}
