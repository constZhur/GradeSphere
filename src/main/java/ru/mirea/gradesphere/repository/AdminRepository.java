package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.gradesphere.model.users.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
