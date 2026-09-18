package com.tylerandrews.canvas_mini_lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/** 
 * This class represents a course in Canvas. 
 * It is used to deserialize JSON responses from the Canvas API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    private Long id;
    private String name;

    // Getters and setters are required for Jackson to populate these fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}