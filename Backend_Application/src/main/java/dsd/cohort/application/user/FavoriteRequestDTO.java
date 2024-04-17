package dsd.cohort.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteRequestDTO {
  
  private String email;
  private String recipeId;
  
}
