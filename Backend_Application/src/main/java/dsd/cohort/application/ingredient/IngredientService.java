package dsd.cohort.application.ingredient;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    IngredientEntity getIngredientByFoodId(String foodId);

    IngredientEntity ingredientExists(String foodId);

    IngredientEntity createIngredient(IngredientEntity ingredient);

    List<IngredientEntity> getAllIngredients();
}
