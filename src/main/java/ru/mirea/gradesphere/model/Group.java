package ru.mirea.gradesphere.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 4, max = 255, message = "Длина названия группы должна быть между 4 и 255")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Требуется идентификатор кафедры")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
}

