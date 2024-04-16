package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){

        try {
            UserEntity newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newUser);
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't Create User");
        }
    }

    @GetMapping("/finduserbyemail")
    public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String email) {
        UserEntity user = userService.findUserByEmail(email);

        if(user != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");

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
    @GetMapping("/getuserfavorites")
    public Set<RecipeEntity> getUserFavorites(@RequestBody String email) {
        return userService.getUserFavorites(email);
    }

    @GetMapping("/getgrocerylist")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@RequestBody String email) {
        return userService.getGroceryList(email);
    }

    @DeleteMapping("/removefromgrocerylist")
    public boolean removeFromGroceryList(@RequestBody String email, @RequestBody String foodId) {
        return userService.removeFromGroceryList(email, foodId);
    }
}
