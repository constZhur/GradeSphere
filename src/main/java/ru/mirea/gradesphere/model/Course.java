package ru.mirea.gradesphere.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import ru.mirea.gradesphere.model.users.*;

import java.util.List;

@Entity
@Data
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "course_group",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnore
    private List<Group> groups;

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Teacher> teachers;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private CourseMaterial courseMaterial;

//    @PostPersist
//    private void createCourseMaterial() {
//        if (courseMaterial == null) {
//            courseMaterial = new CourseMaterial();
//            courseMaterial.setCourse(this);
//        }
//    }

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PracticalWork> practicalWorks;

}
