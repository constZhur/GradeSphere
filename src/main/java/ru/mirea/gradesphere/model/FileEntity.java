package ru.mirea.gradesphere.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_material_id")
    private CourseMaterial courseMaterial;

    @Column(name = "file_id")
    private String fileId;
}
