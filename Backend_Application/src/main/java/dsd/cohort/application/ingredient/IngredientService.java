package dsd.cohort.application.ingredient;

import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    IngredientEntity getIngredientById(Long id);

    IngredientEntity getIngredientByDescription(String description);

    boolean createIngredient(IngredientEntity ingredient);

}
