package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import lombok.Data;
import ru.mirea.gradesphere.model.Course;
import ru.mirea.gradesphere.model.Group;

import java.util.List;

@Entity
@Data
@Table(name = "students")
public class Student extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
