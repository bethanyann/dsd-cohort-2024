package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

    // Will hold a list of recipes that meet the search criteria
    List<RecipeEntity> getRecipeByName(String name);

    RecipeEntity getRecipeByRecipeId(String recipeId);

    RecipeEntity createRecipe(String recipeId);

    List<RecipeEntity> getAllRecipes(); // TODO: add pagination

    List<String> getRecipeNames();

}
