package dsd.cohort.application.ingredient;

import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity getIngredientByFoodId(String foodId) {
        return ingredientExists(foodId);
    }

    @Override
    public IngredientEntity ingredientExists(String foodId) {
        IngredientEntity ingredient = ingredientRepository.findByFoodId(foodId);

        if(ingredient == null) {
            return createIngredient(foodId);
        }

        return ingredient;
    }

    @Override
    public IngredientEntity createIngredient(String foodId) {

        // TODO: API Call

        IngredientEntity ingredientEntity = new IngredientEntity();

        // TODO: Set all fields

        ingredientRepository.save(ingredientEntity);
        return null;
    }
}
