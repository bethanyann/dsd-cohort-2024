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
    public RecipeEntity getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    // Return a recipe if it exists in the database by name
    @Override
    public RecipeEntity getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public boolean createRecipe(RecipeEntity recipe) {

        // validate recipe is not null
        if (recipe == null) {
            return false;
        }

        // validate recipe does not exist
        if (recipeRepository.existsById(recipe.getId())) {
            return false;
        }

        // save recipe to database
        recipeRepository.save(recipe);
        return true;
    }

    // TODO: add pagination
    // return all recipes from database
    @Override
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
