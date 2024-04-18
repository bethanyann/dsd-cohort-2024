package dsd.cohort.application.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity getIngredientByFoodId(String foodId) {

        return ingredientRepository.findByFoodId(foodId);
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

        ingredientRepository.save(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientEntity> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
