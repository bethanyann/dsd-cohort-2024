package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;


@Service
public interface UserService {

    UserEntity findUserByEmail(String email);

    boolean userExists(String email);

    UserEntity createUser(UserEntity user);

    boolean addRecipe(String email, String recipeId);

    boolean deleteRecipe(String email, String recipeId);

    Set<RecipeEntity> getUserFavorites(String email);

    Set<IngredientEntity> getGroceryList(String email);

    boolean removeFromGroceryList(String email, String foodId);

    List<UserEntity> getAll();
}
