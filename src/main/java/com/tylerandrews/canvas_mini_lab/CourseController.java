package com.tylerandrews.canvas_mini_lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
/**
 * Controller class that handles HTTP requests related to courses and assignments.
 * It interacts with the CanvasService to fetch data from the Canvas API and prepares it for the view layer.
 */
@Controller
public class CourseController {

    private final CanvasService canvasService;

    /**
     * Constructs a new CourseController with the specified CanvasService.
     *
     * @param canvasService the CanvasService to use for interacting with the Canvas API
     */
    @Autowired
    public CourseController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    /**
     * Handles GET requests to the root URL ("/") and retrieves a list of active courses.
     * The courses are added to the model and displayed in the "courses" view.
     *
     * @param model the Model object used to pass data to the view
     * @return the name of the view template to render (courses.html)
     */
    @GetMapping("/")
    public String showCourses(Model model) {
        List<Course> courses = canvasService.getCourses();
        model.addAttribute("courses", courses);
        return "courses"; // looks for templates/courses.html
    }

    /**
     * Handles GET requests to the "/assignments" URL and retrieves a list of assignments for a specific course.
     * The assignments are sorted by due date, with assignments without a due date appearing at the end.
     * The sorted assignments are added to the model and displayed in the "assignments" view.
     *
     * @param courseId the ID of the course for which to retrieve assignments
     * @param model    the Model object used to pass data to the view
     * @return the name of the view template to render (assignments.html)
     */
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