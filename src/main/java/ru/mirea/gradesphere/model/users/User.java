package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import ru.mirea.gradesphere.utils.Role;

@MappedSuperclass
public abstract class User {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
