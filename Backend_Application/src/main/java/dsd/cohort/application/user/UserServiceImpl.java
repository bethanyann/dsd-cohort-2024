package dsd.cohort.application.user;

import java.util.List;

import org.springframework.stereotype.Service;

import dsd.cohort.application.recipe.RecipeEntity;
import dsd.cohort.application.recipe.RecipeRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository usersRepository;
    private RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository usersRepository, RecipeRepository recipeRepository) {
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
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
    public UserEntity createUser(UserEntity user) {
        UserEntity newUser = usersRepository.save(user);
        if(newUser != null){
            return newUser;
        }
        return null;
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
}
