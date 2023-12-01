package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "admins")
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
