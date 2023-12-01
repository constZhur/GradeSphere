package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.gradesphere.model.users.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
