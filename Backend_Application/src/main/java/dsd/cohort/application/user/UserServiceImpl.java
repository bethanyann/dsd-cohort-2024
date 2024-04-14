package dsd.cohort.application.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.ingredient.IngredientRepository;
import dsd.cohort.application.recipe.RecipeEntity;
import dsd.cohort.application.recipe.RecipeRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository usersRepository;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public UserServiceImpl(UserRepository usersRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public ResponseEntity<UserEntity> findUserByEmail(String email) {
        if(userExists(email)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(usersRepository.findByEmail(email));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }

    @Override
    public boolean userExists(String email) {
        UserEntity user = usersRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public UserEntity createUser(UserEntity user) throws IllegalArgumentException{
        return usersRepository.save(user);
    }

    @Override
    public boolean addRecipe(String email, String recipeId) {

        UserEntity user = usersRepository.findByEmail(email);
        RecipeEntity recipe = recipeRepository.findByRecipeId(recipeId);

        if (user != null && recipe != null) {
            user.getFavoriteRecipes().add(recipe);
            usersRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteRecipe(String email, String recipeId) {

        UserEntity user = usersRepository.findByEmail(email);
        RecipeEntity recipe = recipeRepository.findByRecipeId(recipeId);

        if (user != null && recipe != null) {

            if (!user.getFavoriteRecipes().contains(recipe)) {
                return false;
            }

            user.getFavoriteRecipes().remove(recipe);
            usersRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public List<UserEntity> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Set<RecipeEntity> getUserFavorites(String email) {

        UserEntity user = usersRepository.findByEmail(email);
        if (user != null) {
            return user.getFavoriteRecipes();
        }
        return null;
    }

    @Override
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(String email) {

        UserEntity user = usersRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user.getGroceryList());
        }
        return null;
    }

    @Override
    public boolean removeFromGroceryList(String email, String foodId) {
        UserEntity user = usersRepository.findByEmail(email);
        IngredientEntity ingredient = ingredientRepository.findByFoodId(foodId);

        if (user != null && ingredient != null) {

            if (!user.getGroceryList().contains(ingredient)) {
                return false;
            }

            user.getGroceryList().remove(ingredient);
            usersRepository.save(user);
            return true;
        }

        return false;
    }
}
