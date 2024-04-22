package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @ApiResponse(responseCode = "200", description = "All recipes returned",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Could not get recipes",
            content = @Content()),
    })
    @GetMapping("/")
    public List<RecipeEntity> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @Operation(summary = "Get a recipe by id", description = "Get recipe by id. Recipe id should be in the format of 'recipe_bmyxrshbfao9s1amjrvhoauob6mo'")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDTO.class))),
        @ApiResponse(responseCode = "500", description = "Recipe not found",
            content = @Content),
    })
    @GetMapping("/{recipeId}")
    public RecipeEntity getRecipeById(@PathVariable String recipeId) {
        RecipeEntity recipe = recipeService.getRecipeByRecipeId(recipeId);
        return recipe;
    }

    @Operation(summary = "Get all recipes by name partial", description = "Get all recipes by name partial(i.e. 'chicken' will return all recipes with 'chicken' in the name)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipes found",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDTO.class)))),
        @ApiResponse(responseCode = "500", description = "No recipes found with that name",
            content = @Content),
    })
    @GetMapping("/search/{name}")
    public ResponseEntity<List<RecipeEntity>> getRecipeByName(@PathVariable String name) {
        List<RecipeEntity> recipes = recipeService.getRecipeByName(name);

        if (recipes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }

    @Operation(summary = "Get all recipe names", description = "Get all recipe names, returned in an array")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe names returned",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "500", description = "Could not get recipe names",
            content = @Content()),
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
