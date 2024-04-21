package dsd.cohort.application.ingredient;

import java.util.List;

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
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(summary = "Get ingredient by id", description = "Get ingredient by id. Ingredient id should be in the format of 'food_bmyxrshbfao9s1amjrvhoauob6mo'")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredient found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDTO.class))),
        @ApiResponse(responseCode = "500", description = "Ingredient not found",
            content = @Content),
    })
    @GetMapping("/{id}")
    public IngredientEntity getIngredientById(@PathVariable String id) {
        return ingredientService.getIngredientByFoodId(id);
    }

    @Operation(summary = "Get all ingredients", description = "Retrieves all ingredients in the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredients found",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IngredientDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Internal server error, could not get ingredients",
            content = @Content),
    })
    @GetMapping("/")
    public List<IngredientEntity> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

}
