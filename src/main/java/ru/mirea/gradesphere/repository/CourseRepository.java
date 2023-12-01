package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.gradesphere.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
