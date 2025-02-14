package main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static void main(String[] args) throws Exception {
        main.config.DatabaseInitializer.initializeDatabase();
        main.config.HttpServerConfig.startServer();
        serveFrontend();
    }

    private static void serveFrontend() throws IOException {
        HttpServer staticServer = HttpServer.create(new InetSocketAddress(8081), 0);

        staticServer.createContext("/", exchange -> {
            String filePath = "frontend" + (exchange.getRequestURI().getPath().equals("/") ? "/index.html" : exchange.getRequestURI().getPath());
            Path path = Path.of(filePath);

            if (!Files.exists(path)) {
                exchange.sendResponseHeaders(404, 0);
                exchange.getResponseBody().write("404 Not Found".getBytes());
                exchange.getResponseBody().close();
                return;
            }

            byte[] fileContent = Files.readAllBytes(path);
            exchange.getResponseHeaders().set("Content-Type", getMimeType(filePath));
            exchange.sendResponseHeaders(200, fileContent.length);
            exchange.getResponseBody().write(fileContent);
            exchange.getResponseBody().close();
        });

        staticServer.start();
        System.out.println("Frontend available at: http://localhost:8081");
    }

    private static String getMimeType(String filePath) {
        Map<String, String> mimeTypes = new HashMap<>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("js", "application/javascript");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("json", "application/json");

        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
        return mimeTypes.getOrDefault(extension, "application/octet-stream");
    }
}
