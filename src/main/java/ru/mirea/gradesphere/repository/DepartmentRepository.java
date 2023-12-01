package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.gradesphere.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
