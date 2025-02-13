package main.config;

import com.sun.net.httpserver.HttpServer;
import main.controller.UserController;
import main.controller.WorkoutController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerConfig {
    public static void startServer() throws IOException {
        // This creates an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Register the routes for the API
        server.createContext("/users", new UserController());
        server.createContext("/workouts", new WorkoutController());

        server.setExecutor(null); // Default executor
        server.start();
        System.out.println("API server started on port 8080...");
    }
}
