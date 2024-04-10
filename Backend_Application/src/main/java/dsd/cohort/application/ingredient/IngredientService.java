package dsd.cohort.application.ingredient;

import org.springframework.stereotype.Service;

@Service
public interface IngredientService {

    IngredientEntity getIngredientById(Long id);

    IngredientEntity getIngredientByName(String name);

    boolean createIngredient(IngredientEntity ingredient);

}
