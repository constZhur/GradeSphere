package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mirea.gradesphere.utils.Role;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(max = 255, message = "Имя не должно превосходить длины 255 символов")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(max = 255, message = "Фамилия не должна превосходить длины 255 символов")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Эл. почта не может быть пустой")
    @Email(message = "Некорректный формат почты")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 255, message = "Длина пароля должна быть между 6 и 255")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Роль не может быть нулевой")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "enabled")
    private boolean enabled = true;

    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
