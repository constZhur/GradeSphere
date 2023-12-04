package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.gradesphere.model.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
