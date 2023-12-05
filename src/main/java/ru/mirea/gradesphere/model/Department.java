package ru.mirea.gradesphere.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.mirea.gradesphere.model.users.Teacher;

import java.util.List;

@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя кафедры не должно быть пустым")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Group> groups;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "head_of_department_id")
    @JsonIgnore
    private Teacher headOfDepartment;
}
