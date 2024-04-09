package dsd.cohort.application.recipe;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public RecipeEntity getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public RecipeEntity getRecipeByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public List<RecipeEntity> getAllRecipes() {
        return null;
    }
}
