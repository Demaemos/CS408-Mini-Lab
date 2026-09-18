package com.tylerandrews.canvas_mini_lab;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
/**
 * Global exception handler for the application.
 * It catches exceptions thrown during the execution of controller methods and provides user-friendly error messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Canvas returned an error status code (401, 403, 404, etc.)
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleCanvasError(HttpClientErrorException ex, Model model) {
        String message;
        if (ex.getStatusCode().value() == 401) {
            message = "Canvas rejected the request — your API token may be missing, invalid, or expired.";
        } else if (ex.getStatusCode().value() == 404) {
            message = "That course or resource could not be found on Canvas.";
        } else {
            message = "Canvas returned an error: " + ex.getStatusCode() + " " + ex.getStatusText();
        }
        model.addAttribute("errorMessage", message);
        return "error";
    }

    // Network failure — Canvas is unreachable, no internet, DNS issue, etc.
    @ExceptionHandler(ResourceAccessException.class)
    public String handleNetworkError(ResourceAccessException ex, Model model) {
        model.addAttribute("errorMessage", "Could not reach Canvas — check your internet connection and try again.");
        return "error";
    }

    // Catch-all for anything else unexpected
    @ExceptionHandler(Exception.class)
    public String handleGenericError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "error";
    }
}