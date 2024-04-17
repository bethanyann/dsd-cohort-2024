package dsd.cohort.application.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public IngredientEntity ingredientExists(String foodId) throws ResponseStatusException {
        IngredientEntity ingredient = ingredientRepository.findByFoodId(foodId);

        if (ingredient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient not found.");
        }

        return ingredient;
    }

    @Override
    public IngredientEntity createIngredient(IngredientEntity ingredient) {

        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientRepository.save(ingredientEntity);
        return ingredientEntity;
    }
}
