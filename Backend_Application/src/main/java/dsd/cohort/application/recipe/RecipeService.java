package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RecipeService {

    RecipeEntity getRecipeById(Long id);

    RecipeEntity getRecipeByName(String name);

    boolean createRecipe(RecipeEntity recipe);

    List<RecipeEntity> getAllRecipes(); // TODO: add pagination

}
