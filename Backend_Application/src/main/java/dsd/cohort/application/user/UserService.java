package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;
import org.springframework.web.client.HttpServerErrorException;


@Service
public interface UserService {

    boolean userExists(String email);

    UserEntity createUser(UserRegisterDTO user) throws HttpServerErrorException.InternalServerError;

    boolean addRecipe(UserDataRequestDTO userDataRequestDTO);

    boolean deleteRecipe(String email, String recipeId);

    Set<RecipeEntity> getUserFavorites(String email);

    Set<IngredientEntity> getGroceryList(String email);

    boolean addGroceryItem(UserDataRequestDTO userDataRequestDTO);

    boolean removeFromGroceryList(UserDataRequestDTO userDataRequestDTO);

    List<UserEntity> getAll();

    UserEntity userauth(UserRequestDTO userRequestDTO);
}
