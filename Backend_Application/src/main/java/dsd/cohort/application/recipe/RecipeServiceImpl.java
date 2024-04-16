package dsd.cohort.application.recipe;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Return a recipe if it exists in the database by id
    @Override
    public RecipeEntity getRecipeByRecipeId(String recipeId) throws ResponseStatusException {
        RecipeEntity recipe = recipeRepository.findByRecipeId(recipeId);

        if (recipe != null) {
            // fetch recipe from API
            // String baseUrl = "https://api.edamam.com/api/recipes/v2/";
            // RestTemplate restTemplate = new RestTemplate();
            // restTemplate.getForEntity(baseUrl, RecipeResponseDTO.class, recipeId);
            // HttpRequest request = HttpRequest.newBuilder();
            // request.header("Content-Type", "application/json");

            RecipeEntity newRecipe = new RecipeEntity();

            newRecipe.setRecipeId(recipe.getRecipeId());
            newRecipe.setName(recipe.getName());
            newRecipe.setDescription(recipe.getDescription());
            newRecipe.setYield(recipe.getYield());
            newRecipe.setTotalTime(recipe.getTotalTime());
            newRecipe.setImageUrl(recipe.getImageUrl());
            newRecipe.setUrl(recipe.getUrl());
            newRecipe.setCalories(recipe.getCalories());
            newRecipe.setCarbs(recipe.getCarbs());
            newRecipe.setFat(recipe.getFat());
            newRecipe.setProtein(recipe.getProtein());

            recipe = newRecipe;

            recipeRepository.save(newRecipe);
        }

        return recipe;
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

