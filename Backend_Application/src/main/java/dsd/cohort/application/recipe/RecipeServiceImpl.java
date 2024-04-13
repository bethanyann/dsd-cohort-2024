package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Return a recipe if it exists in the database by id
    @Override
    public RecipeEntity getRecipeByRecipeId(String recipeId) {
        return recipeRepository.findByRecipeId(recipeId);
    }

    // Return a recipe if it exists in the database by name
    @Override
    public List<RecipeEntity> getRecipeByName(String name) {
        return null;
    }

    @Override
    public RecipeEntity createRecipe(String recipeId) {

        RecipeEntity recipe = new RecipeEntity();

        // save recipe to database
        recipeRepository.save(recipe);
        return null;
    }

    // TODO: add pagination
    // return all recipes from database
    @Override
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

}

