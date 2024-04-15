package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<RecipeEntity> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{recipeId}")
    public RecipeDTO getRecipeById(@PathVariable String recipe) {
        return recipeService.getRecipeByRecipeId(recipe);
    }

    @GetMapping("/search/{name}")
    public String getRecipeByName(@PathVariable String name) {
        return recipeService.getRecipeByName(name).toString();
    }
}
