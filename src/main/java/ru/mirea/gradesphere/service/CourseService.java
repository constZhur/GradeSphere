package ru.mirea.gradesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.gradesphere.exception.EntityNotFoundException;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMaterialService courseMaterialService;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMaterialService courseMaterialService) {
        this.courseRepository = courseRepository;
        this.courseMaterialService = courseMaterialService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        courseMaterialService.createCourseMaterial(savedCourse);

        return savedCourse;
    }

    public Course updateCourse(Long id, Course course) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    public List<Course> getAllCoursesSortedByNameAsc() {
        return courseRepository.findAllByOrderByNameAsc();
    }

    public List<Course> getAllCoursesSortedByNameDesc() {
        return courseRepository.findAllByOrderByNameDesc();
    }

    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findAllByNameIgnoreCaseContaining(name);
    }
}
