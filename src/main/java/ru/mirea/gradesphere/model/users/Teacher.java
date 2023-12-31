package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import lombok.*;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.model.Department;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;
}
