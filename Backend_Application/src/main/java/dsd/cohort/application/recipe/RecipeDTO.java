package dsd.cohort.application.recipe;

import java.util.List;

import dsd.cohort.application.ingredient.IngredientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

  private String recipeId;
  private String name;
  private String description;
  private int yield;
  private int totalTime;
  private String imageUrl;
  private String url;
  private int protein;
  private int fat;
  private int carbs;
  private int calories;
  private List<IngredientDTO> ingredients;

}
