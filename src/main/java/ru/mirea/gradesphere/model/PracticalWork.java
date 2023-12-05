package ru.mirea.gradesphere.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
@Table(name = "practical_works")
public class PracticalWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "file_id")
    private String fileId;

    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 100, message = "Оценка не может быть выше 100")
    @Column(name = "grade")
    private Integer grade;

    @Column(name = "evaluation_status")
    private boolean evaluationStatus;
}
