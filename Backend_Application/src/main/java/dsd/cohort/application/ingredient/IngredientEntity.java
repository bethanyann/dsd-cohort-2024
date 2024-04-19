package dsd.cohort.application.ingredient;

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
@Table(name = "ingredients")
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    // primary search field
    @Column(name = "food_id", unique = true)
    private String foodId;

    @Column(name = "name")
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
  
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    @Override
    public String toString() {
        return "IngredientEntity{" +
                "id=" + id +
                ", foodId='" + foodId + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", quantity='" + quantity + '\'' +
                ", measure=" + measure +
                ", weight=" + weight +
                ", foodCategory=" + foodCategory +
                '}';
    }

}

