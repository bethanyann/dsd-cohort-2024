package dsd.cohort.application.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

  private Long id;
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
