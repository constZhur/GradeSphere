package ru.mirea.gradesphere.model;

import jakarta.persistence.*;
import lombok.*;
import ru.mirea.gradesphere.model.users.Student;

import java.util.List;

@Entity
@Data
@Table(name = "student_groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
}

