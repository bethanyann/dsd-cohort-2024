package dsd.cohort.application.ingredient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Operation(summary = "Get ingredient by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredient found"),
        @ApiResponse(responseCode = "500", description = "Ingredient not found")
    })
    @GetMapping("/{id}")
    public IngredientEntity getIngredientById(@PathVariable String id) {
        return ingredientService.getIngredientByFoodId(id);
    }

    @GetMapping("/")
    public List<IngredientEntity> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

}
