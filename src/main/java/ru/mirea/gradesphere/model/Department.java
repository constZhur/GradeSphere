package ru.mirea.gradesphere.model;

import jakarta.persistence.*;
import lombok.*;
import ru.mirea.gradesphere.model.users.Student;
import ru.mirea.gradesphere.model.users.Teacher;

import java.util.List;

@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Group> groups;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Course> courses;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "head_of_department_id")
    private Teacher headOfDepartment;
}
