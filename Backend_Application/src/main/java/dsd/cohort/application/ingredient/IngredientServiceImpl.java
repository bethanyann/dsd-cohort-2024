package dsd.cohort.application.ingredient;

import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientEntity getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    @Override
    public IngredientEntity getIngredientByName(String name) {
        return ingredientRepository.findByDescription(name);
    }

    @Override
    public boolean createIngredient(IngredientEntity ingredient) {
        if(ingredient==null) {
            return false;
        }

        if(ingredientRepository.existsById(ingredient.getId())) {
            return false;
        }

        ingredientRepository.save(ingredient);
        return true;
    }
}
