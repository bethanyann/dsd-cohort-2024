package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping("/test")
    public List<UserEntity> test(){
        return userService.getAll();
    }


    @PostMapping("/createuser")
    public UserEntity createUser(@RequestBody UserEntity user){
        UserEntity userCreated = userService.createUser(user);
        if(userCreated != null){
        return userCreated;
        }
        return null;
    }

    @GetMapping("/finduserbyemail")
    public UserEntity findUserByEmail(@RequestBody String userEmail){
        return userService.findUserByEmail(userEmail);
    }

    // add a recipe to a users favorites
    @GetMapping("/addRecipe")
    public boolean addRecipe(@RequestBody String email, @RequestBody String recipeId){
        return userService.addRecipe(email, recipeId);
    }

    // delete a recipe from a users favorites
    @DeleteMapping("/deleteRecipe")
    public boolean deleteRecipe(@RequestBody String email, @RequestBody String recipeId){
        return userService.deleteRecipe(email, recipeId);
    }

    // get a users favorites
    @GetMapping("/getUserFavorites")
    public Set<RecipeEntity> getUserFavorites(@RequestBody String email){
        return userService.getUserFavorites(email);
    }

    @GetMapping("/getGroceryList")
    public Set<IngredientEntity> getGroceryList(@RequestBody String email){
        return userService.getGroceryList(email);
    }

    @DeleteMapping("/removeFromGroceryList")
    public boolean removeFromGroceryList(@RequestBody String email, @RequestBody String foodId){
        return userService.removeFromGroceryList(email, foodId);
    }
}
