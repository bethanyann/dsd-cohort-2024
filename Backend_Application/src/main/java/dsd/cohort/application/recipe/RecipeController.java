package dsd.cohort.application.recipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String getAllRecipes() {
        return recipeService.getAllRecipes().toString();
    }

    @GetMapping("/{id}")
    public String getRecipeById(Long id) {
        return recipeService.getRecipeById(id).toString();
    }

    @GetMapping("/name/{name}")
    public String getRecipeByName(String name) {
        return recipeService.getRecipeByName(name).toString();
    }
}
