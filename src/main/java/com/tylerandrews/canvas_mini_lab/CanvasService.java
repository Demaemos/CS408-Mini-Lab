package com.tylerandrews.canvas_mini_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CanvasService {

    private final CanvasConfig canvasConfig;
    private final RestClient restClient;

    // Matches: <URL>; rel="next"
    private static final Pattern NEXT_LINK_PATTERN =
            Pattern.compile("<([^>]+)>;\\s*rel=\"next\"");

    @Autowired
    public CanvasService(CanvasConfig canvasConfig) {
        this.canvasConfig = canvasConfig;
        this.restClient = RestClient.create();
    }

    public List<Course> getCourses() {
        return fetchAllPages(canvasConfig.getCanvasBaseUrl() + "/courses?enrollment_state=active", Course[].class);
    }

    public List<Assignment> getAssignments(Long courseId) {
        String url = canvasConfig.getCanvasBaseUrl() + "/courses/" + courseId + "/assignments";
        return fetchAllPages(url, Assignment[].class);
    }

    /**
     * Follows Canvas's Link header pagination, fetching every page
     * until there is no "next" link left, and combining all results.
     */
    private <T> List<T> fetchAllPages(String initialUrl, Class<T[]> arrayType) {
        List<T> allResults = new ArrayList<>();
        String currentUrl = initialUrl;

        while (currentUrl != null) {
            ResponseEntity<T[]> response = restClient.get()
                    .uri(currentUrl)
                    .header("Authorization", "Bearer " + canvasConfig.getCanvasApiToken())
                    .retrieve()
                    .toEntity(arrayType);

            T[] pageResults = response.getBody();
            if (pageResults != null) {
                allResults.addAll(List.of(pageResults));
            }

            currentUrl = extractNextUrl(response.getHeaders().getFirst("Link"));
        }

        return allResults;
    }

    private String extractNextUrl(String linkHeader) {
        if (linkHeader == null) {
            return null;
        }
        Matcher matcher = NEXT_LINK_PATTERN.matcher(linkHeader);
        return matcher.find() ? matcher.group(1) : null;
    }
}