package ru.mirea.gradesphere.model.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@DiscriminatorValue("ADMIN")
public class Admin extends User {
}
