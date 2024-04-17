package dsd.cohort.application.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * The annotations below help with handling boilerplate code for the users entity
 * NoArgs/AllArgs use is dependent on how you create a users.
 * NoArgs will be for creating a null users if any logic needs to happen during construction
 * AllArgs will be for creating a new users with all fields
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; // TODO: encrypt password

    @OneToMany
    private Set<RecipeEntity> favoriteRecipes = new HashSet<>();

    @OneToMany
    private Set<IngredientEntity> groceryList = new HashSet<>();

    // TODO: add preferences
}
