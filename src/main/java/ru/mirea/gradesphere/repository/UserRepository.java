package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.gradesphere.model.users.User;
import ru.mirea.gradesphere.utils.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Optional<User> findByIdAndRole(Long teacherId, Role role);
}
