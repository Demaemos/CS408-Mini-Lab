package com.tylerandrews.canvas_mini_lab;

import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class CanvasConfig {

    private String canvasApiToken;
    private final String canvasBaseUrl = "https://boisestatecanvas.instructure.com/api/v1";

    public CanvasConfig() {
        loadEnvFile();
    }

    private void loadEnvFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                int equalsIndex = line.indexOf('=');
                if (equalsIndex == -1) continue;

                String key = line.substring(0, equalsIndex).trim();
                String value = line.substring(equalsIndex + 1).trim();

                if (key.equals("CANVAS_API_TOKEN")) {
                    this.canvasApiToken = value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read .env file. Make sure it exists in the project root.", e);
        }

        if (canvasApiToken == null || canvasApiToken.isEmpty()) {
            throw new RuntimeException("CANVAS_API_TOKEN not found in .env file.");
        }
    }

    public String getCanvasApiToken() {
        return canvasApiToken;
    }

    public String getCanvasBaseUrl() {
        return canvasBaseUrl;
    }
}