package dsd.cohort.application.ingredient;

import java.util.HashSet;
import java.util.Set;

import dsd.cohort.application.recipe.RecipeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    private Long id;

    @Column(name = "food_id", unique = true)
    private String foodId;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private int calories;

    @Column(name = "protein")
    private int protein;

    @Column(name = "fat")
    private int fat;

    @Column(name = "carbs")
    private int carbs;

    @Column(name = "fiber")
    private int fiber;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes = new HashSet<>();
        
}
