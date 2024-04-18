package dsd.cohort.application.recipe;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dsd.cohort.application.config.ApiDetailsImpl;
import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.ingredient.IngredientRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private ApiDetailsImpl apiDetails;

    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
            ApiDetailsImpl apiDetails) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.apiDetails = apiDetails;
    }

    // TODO: add pagination
    // return all recipes from database
    @Override
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Return a recipe if it exists in the database by id
    @Override
    public RecipeEntity getRecipeByRecipeId(String recipeId) throws ResponseStatusException {
        RecipeEntity recipe = recipeRepository.findByRecipeId(recipeId);

        if (recipe == null) {
            try {
                recipe = createRecipe(recipeId);
            } catch (ResponseStatusException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch recipe.");
            }
        }

        return recipe;
    }

    // Return a recipe if it exists in the database by name
    @Override
    public List<RecipeEntity> getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public RecipeEntity createRecipe(String recipeId) {

        RecipeEntity recipe;
        // fetch recipe
        try {
            recipe = fetchRecipe(recipeId);
        } catch (JsonMappingException e) {
            System.out.println("Mapping error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Issue mapping API response.");
        } catch (JsonProcessingException e) {
            System.out.println("Parsing error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Issue parsing API response.");
        }

        return recipe;
    }

    /**
     * Fetches a recipe from the Edamam API by its ID and returns a RecipeEntity
     * object.
     *
     * @param recipeId the ID of the recipe to fetch
     * @return a RecipeEntity object representing the fetched recipe
     * @throws ResponseStatusException if unable to parse the recipe
     */

    public RecipeEntity fetchRecipe(String recipeId)
            throws ResponseStatusException, JsonProcessingException, JsonMappingException {

        // build url
        String baseUrl = "";
        baseUrl += "https://api.edamam.com/api/recipes/v2";
        baseUrl += "/" + recipeId;
        baseUrl += apiDetails.getApiDetails();

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(baseUrl, String.class);

        // declare variables for parsing
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        RecipeEntity newRecipe = new RecipeEntity();
        // parse response to json
        jsonNode = mapper.readTree(response);

        newRecipe.setName(jsonNode.findValue("label").textValue());
        newRecipe.setDescription(jsonNode.findValue("label").textValue());
        newRecipe.setRecipeId(recipeId);
        newRecipe.setImageUrl(jsonNode.findValue("image").textValue());
        newRecipe.setUrl(jsonNode.findValue("url").textValue());
        newRecipe.setYield(jsonNode.findValue("yield").intValue());
        newRecipe.setTotalTime(jsonNode.findValue("totalTime").intValue());

        // create a formatter to round doubles to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        // get nutrients from json
        JsonNode nutrients = jsonNode.findValue("totalNutrients");

        Double caloriesD = jsonNode.findValue("calories").doubleValue();
        newRecipe.setCalories(Double.parseDouble(df.format(caloriesD)));

        double fats = nutrients
                .findValue("FAT")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setFat(Double.parseDouble(df.format(fats)));

        double protein = nutrients
                .findValue("PROCNT")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setProtein(Double.parseDouble(df.format(protein)));

        double carbs = nutrients
                .findValue("CHOCDF")
                .findValue("quantity")
                .doubleValue();
        newRecipe.setCarbs(Double.parseDouble(df.format(carbs)));

        // get ingredients from json
        JsonNode ingredientsJson = jsonNode.findValue("ingredients");
        Set<IngredientEntity> ingredients = parseIngredients(ingredientsJson, recipeId);
        newRecipe.setIngredients(ingredients);

        System.out.println("\n\nSuccessful parse\n\n");

        return newRecipe;

    }

    /**
     * Parses the given JSON node representing ingredients and creates a Set of
     * IngredientEntity objects.
     *
     * @param ingredientsJson the JSON node representing ingredients
     * @return a Set of IngredientEntity objects parsed from the JSON node
     */
    public Set<IngredientEntity> parseIngredients(JsonNode ingredientsJson, String recipeId) {
        Set<IngredientEntity> ingredients = new HashSet<>();
        DecimalFormat df = new DecimalFormat("#.00");

        if (ingredientsJson.isArray()) {
            for (JsonNode ingredient : ingredientsJson) {

                String foodId = ingredient.findValue("foodId").textValue();
                IngredientEntity existingIngredient = ingredientRepository.findByFoodId(foodId);

                if (existingIngredient != null) {
                    ingredients.add(existingIngredient);
                    continue;
                }

                IngredientEntity newIngredient = new IngredientEntity();

                newIngredient.setFoodId(ingredient.findValue("foodId").textValue());
                newIngredient.setText(ingredient.findValue("text").textValue());
                newIngredient.setQuantity(ingredient.findValue("quantity").intValue());
                newIngredient.setMeasure(ingredient.findValue("measure").textValue());
                newIngredient.setName(ingredient.findValue("food").textValue());
                newIngredient.setFoodCategory(ingredient.findValue("foodCategory").textValue());
                newIngredient.setImageUrl(ingredient.findValue("image").textValue());

                Double weight = ingredient.findValue("weight").doubleValue();
                newIngredient.setWeight(Double.parseDouble(df.format(weight)));

                ingredients.add(newIngredient);

                ingredientRepository.save(newIngredient);

            }
        }

        return ingredients;

    }
}
