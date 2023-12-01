package ru.mirea.gradesphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.gradesphere.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
