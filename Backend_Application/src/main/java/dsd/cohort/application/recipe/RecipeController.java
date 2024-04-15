package dsd.cohort.application.recipe;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/{id}")
    public String getRecipeById(@RequestBody String id) {
        return recipeService.getRecipeByRecipeId(id).toString();
    }

    @GetMapping("/name/{name}")
    public String getRecipeByName(@RequestBody String name) {
        return recipeService.getRecipeByName(name).toString();
    }
}
