package ru.mirea.gradesphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.service.CourseService;
import ru.mirea.gradesphere.service.FileStorageService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final FileStorageService fileStorageService;

    @Autowired
    public CourseController(CourseService courseService, FileStorageService fileStorageService) {
        this.courseService = courseService;
        this.fileStorageService = fileStorageService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable(name = "id") Long id) {
        return courseService.getCourseById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable(name = "id") Long id,
                               @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable(name = "id") Long id) {
        courseService.deleteCourse(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/sorted_by_name_asc")
    public List<Course> getAllCoursesSortedByNameAsc() {
        return courseService.getAllCoursesSortedByNameAsc();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/sorted_by_name_desc")
    public List<Course> getAllCoursesSortedByNameDesc() {
        return courseService.getAllCoursesSortedByNameDesc();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT', 'TEACHER')")
    @GetMapping("/search")
    public List<Course> searchCoursesByName(@RequestParam String name) {
        return courseService.searchCoursesByName(name);
    }

    @GetMapping("/{courseId}/files")
    public List<String> getAllFilesForCourse(@PathVariable(name = "courseId") Long courseId) {
        return fileStorageService.getAllFiles(courseId);
    }
}
