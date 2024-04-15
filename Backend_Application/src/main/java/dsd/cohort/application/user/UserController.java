package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public List<UserEntity> test() {
        return userService.getAll();
    }

    @PostMapping("/createuser")
    public UserEntity createUser(@RequestBody UserEntity user) throws IllegalArgumentException {

        try {
            UserEntity userCreated = userService.createUser(user);
            return userCreated;
        } catch (IllegalArgumentException e) {
            // TODO: handle exception, unable to create user
            return null;
        }
    }

    @GetMapping("/finduserbyemail")
    public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String userEmail) {
        return userService.findUserByEmail(userEmail);
    }

    // add a recipe to a users favorites
    @GetMapping("/addrecipe")
    public boolean addRecipe(@RequestBody String email, @RequestBody String recipeId) {
        return userService.addRecipe(email, recipeId);
    }

    // delete a recipe from a users favorites
    @DeleteMapping("/deleterecipe")
    public boolean deleteRecipe(@RequestBody String email, @RequestBody String recipeId) {
        return userService.deleteRecipe(email, recipeId);
    }

    // get a users favorites
    @GetMapping("/getuserfavorites/{email}")
    public Set<RecipeEntity> getUserFavorites(@PathVariable String email) {
        return userService.getUserFavorites(email);
    }

    @GetMapping("/getgrocerylist")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@PathVariable String email) {
        return userService.getGroceryList(email);
    }

    @DeleteMapping("/removefromgrocerylist/{email}/{foodId}")
    public boolean removeFromGroceryList(@PathVariable String email, @PathVariable String foodId) {
        return userService.removeFromGroceryList(email, foodId);
    }
}
