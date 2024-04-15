package dsd.cohort.application.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponseDTO {

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
  
}
