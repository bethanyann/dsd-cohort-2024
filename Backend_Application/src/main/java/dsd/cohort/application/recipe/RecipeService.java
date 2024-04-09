package dsd.cohort.application.recipe;

import java.util.List;

public interface RecipeService {

    RecipeEntity getRecipeById(Long id);

    RecipeEntity getRecipeByName(String name);

    List<RecipeEntity> getAllRecipes(); // TODO: add pagination

}
