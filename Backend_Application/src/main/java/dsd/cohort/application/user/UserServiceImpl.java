package dsd.cohort.application.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.stereotype.Service;

import dsd.cohort.application.Utils.Utility;
import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.ingredient.IngredientRepository;
import dsd.cohort.application.recipe.RecipeEntity;
import dsd.cohort.application.recipe.RecipeRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository usersRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final Utility utility;

    public UserServiceImpl(UserRepository usersRepository, RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository, Utility utility) {
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.utility = utility;
    }

    @Override
    public boolean userExists(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserEntity createUser(UserRegisterDTO user) {

        usersRepository.findByEmail(user.getEmail()).orElseThrow();

        UserEntity newUser = new UserEntity();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(utility.encryptString(user.getPassword()));

        return usersRepository.save(newUser);
    }

    @Override
    public boolean addRecipe(UserDataRequestDTO userDataRequestDTO) {

        UserEntity user = usersRepository.findByEmail(userDataRequestDTO.getEmail()).orElseThrow();
        RecipeEntity recipe = recipeRepository.findByRecipeId(userDataRequestDTO.getId());

        if (userExists(userDataRequestDTO.getEmail()) && recipe != null) {
            user.getFavoriteRecipes().add(recipe);
            usersRepository.save(user);
            //Temporary add all recipe ingredients to Grocery List
            this.addGroceryItems(user, recipe);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteRecipe(String email, String recipeId) {

        UserEntity user = usersRepository.findByEmail(email).orElseThrow();
        RecipeEntity recipe = recipeRepository.findByRecipeId(recipeId);

        if (userExists(email) && recipe != null) {

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

        UserEntity user = usersRepository.findByEmail(email).orElseThrow();
        if (userExists(email)) {
            return user.getFavoriteRecipes();
        }
        return null;
    }

    @Override
    public boolean addGroceryItem(UserDataRequestDTO userDataRequestDTO) {

        UserEntity user = usersRepository.findByEmail(userDataRequestDTO.getEmail()).orElseThrow();
        IngredientEntity ingredient = ingredientRepository.findByFoodId(userDataRequestDTO.getId());
        user.getGroceryList().add(ingredient);
        usersRepository.save(user);

        return user.getGroceryList().contains(ingredient);

    }

    public void addGroceryItems(UserEntity user, RecipeEntity recipe) {

        for (IngredientEntity ingredient : recipe.getIngredients()) {
            user.getGroceryList().add(ingredient);
        }
        usersRepository.save(user);
    }

    @Override
    public Set<IngredientEntity> getGroceryList(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow()
                .getGroceryList();
    }

    @Override
    public boolean removeFromGroceryList(UserDataRequestDTO userDataRequestDTO) {
        UserEntity user = usersRepository.findByEmail(userDataRequestDTO.getEmail()).orElseThrow();
        IngredientEntity ingredient = ingredientRepository.findByFoodId(userDataRequestDTO.getId());

        if (userExists(userDataRequestDTO.getEmail()) && ingredient != null) {

            if (!user.getGroceryList().contains(ingredient)) {
                return false;
            }

            user.getGroceryList().remove(ingredient);
            usersRepository.save(user);
            return true;
        }

        return false;
    }

    /**
     * Authenticates a user based on the provided UserRequestDTO by encrypting
     * the password and comparing it to the stored encrypted password.
     * 
     * WARNING: THIS METHOD IS A DEMO METHOD AND SHOULD NOT BE USED IN PRODUCTION
     *
     * @param userRequestDTO the UserRequestDTO containing user credentials
     * @return true if the user authentication is successful
     * @throws NoSuchElementException if the user is not found
     */
    @Override
    public UserEntity userauth(UserRequestDTO userRequestDTO) throws NoSuchElementException {

        if (!userExists(userRequestDTO.getEmail())) {
            throw new NoSuchElementException("User not found");
        }

        UserEntity user = usersRepository.findByEmail(userRequestDTO.getEmail()).orElseThrow();
        if (user.getPassword().equals(utility.encryptString(userRequestDTO.getPassword()))) {
            return user;
        }
        return null;
    }
}
