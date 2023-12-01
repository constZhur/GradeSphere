package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/sorted_by_name_asc")
    public List<Course> getAllCoursesSortedByNameAsc() {
        return courseService.getAllCoursesSortedByNameAsc();
    }

    @GetMapping("/sorted_by_name_desc")
    public List<Course> getAllCoursesSortedByNameDesc() {
        return courseService.getAllCoursesSortedByNameDesc();
    }

    @GetMapping("/search")
    public List<Course> searchCoursesByName(@RequestParam String name) {
        return courseService.searchCoursesByName(name);
    }
}
