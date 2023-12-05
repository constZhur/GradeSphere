package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.gradesphere.model.CourseMaterial;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
