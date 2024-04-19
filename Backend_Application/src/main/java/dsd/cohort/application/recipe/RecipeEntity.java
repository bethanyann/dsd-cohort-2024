package dsd.cohort.application.recipe;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import dsd.cohort.application.ingredient.IngredientEntity;
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
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(name = "recipe_id", unique = true)
    private String recipeId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "yield")
    private Integer yield;

    @Column(name = "total_time")
    private Integer totalTime; // in minutes

    @Column(name = "image_url", length = 2083)
    private String imageUrl;

    @Column(name = "url", length = 2083)
    private String url;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "carbs")
    private double carbs;

    @Column(name = "calories")
    private double calories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<IngredientEntity> ingredients = new HashSet<>();

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id=" + id +
                ", recipeId='" + recipeId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", yield=" + yield +
                ", totalTime=" + totalTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", calories=" + calories +
                ", ingredients=" + ingredients +
                '}';
    }
}
