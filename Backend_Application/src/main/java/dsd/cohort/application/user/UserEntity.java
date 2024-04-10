package dsd.cohort.application.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The annotations below help with handling boilerplate code for the user entity
 * NoArgs/AllArgs use is dependent on how you create a user.
 * NoArgs will be for creating a null user if any logic needs to happen during construction
 * AllArgs will be for creating a new user with all fields
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    // Spring will generate a unique id automagically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false) // reference the column in the database
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password; // TODO: encrypt password

    // TODO: add grocery list and preferences
}
