package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("admin")
public class Admin extends User {
}
