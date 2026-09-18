package com.tylerandrews.canvas_mini_lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/** 
 * This class represents an assignment in a course. 
 * It is used to deserialize JSON responses from the Canvas API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignment {
    private Long id;
    private String name;

    /**
     * The due date of the assignment in ISO 8601 format.
     * This field is mapped from the JSON property "due_at".
     */
    @JsonProperty("due_at")
    private String dueAt;

    /**
     * The maximum number of points the assignment is worth.
     * This field is mapped from the JSON property "points_possible".
     */
    @JsonProperty("points_possible")
    private Double pointsPossible;

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

    public String getDueAt() {
        return dueAt;
    }

    public void setDueAt(String dueAt) {
        this.dueAt = dueAt;
    }

    public Double getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(Double pointsPossible) {
        this.pointsPossible = pointsPossible;
    }
}