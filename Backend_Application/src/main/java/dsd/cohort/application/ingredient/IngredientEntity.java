package dsd.cohort.application.ingredient;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    // primary search field
    @Column(name = "food_id", unique = true)
    private String foodId;

    @Column(name = "food")
    private String name;

    // represents the name of the ingredient
    @Column(name = "text")
    private String text;

    @Column(name = "image_url", length = 2083)
    private String imageUrl;

    // represents the count of measures
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "measure", nullable = true)
    private String measure;

    @Column(name = "weight")
    private double weight;

    @Column(name = "food_category")
    private String foodCategory;

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes = new HashSet<>();
        
}
