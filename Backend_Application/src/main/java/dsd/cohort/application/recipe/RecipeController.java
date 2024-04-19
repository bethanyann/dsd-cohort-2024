package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Get all recipes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All recipes returned"),
        @ApiResponse(responseCode = "500", description = "Could not get recipes")
    })
    @GetMapping("/")
    public List<RecipeEntity> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @Operation(summary = "Get a recipe by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe found"),
        @ApiResponse(responseCode = "500", description = "Recipe not found")
    })
    @GetMapping("/{recipeId}")
    public RecipeEntity getRecipeById(@PathVariable String recipeId) {
        RecipeEntity recipe = recipeService.getRecipeByRecipeId(recipeId);
        return recipe;
    }

    @Operation(summary = "Get all recipes by name partial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipes found"),
        @ApiResponse(responseCode = "500", description = "Recipe not found")
    })
    @GetMapping("/search/{name}")
    public ResponseEntity<List<RecipeEntity>> getRecipeByName(@PathVariable String name) {
        List<RecipeEntity> recipes = recipeService.getRecipeByName(name);

        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }

    @Operation(summary = "Get all recipe names")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe names returned"),
        @ApiResponse(responseCode = "500", description = "Could not get recipe names")
    })
    @GetMapping("/names")
    public ResponseEntity<List<String>> getRecipeNames() {
        List<String> recipeNames = recipeService.getRecipeNames();
        
        if (recipeNames.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipeNames);

    }
}
