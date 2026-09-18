package com.tylerandrews.canvas_mini_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
public class CourseController {

    private final CanvasService canvasService;

    @Autowired
    public CourseController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @GetMapping("/")
    public String showCourses(Model model) {
        List<Course> courses = canvasService.getCourses();
        model.addAttribute("courses", courses);
        return "courses"; // looks for templates/courses.html
    }

    @GetMapping("/assignments")
    public String showAssignments(@RequestParam Long courseId, Model model) {
        List<Assignment> assignments = canvasService.getAssignments(courseId);

        // Sort by due date; assignments with no due date go to the end
        assignments.sort(Comparator.comparing(
                Assignment::getDueAt,
                Comparator.nullsLast(Comparator.naturalOrder())
        ));

        model.addAttribute("assignments", assignments);
        return "assignments"; // looks for templates/assignments.html
    }
}