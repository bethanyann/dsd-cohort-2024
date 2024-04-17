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

    private final UserService userService;

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
        try {
            UserEntity user = userService.findUserByEmail(email);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }
    }

    // add a recipe to a users favorites
    @PostMapping("/addrecipetofavorites")
    public ResponseEntity<String> addRecipe(@RequestBody String email, @RequestBody String recipeId) {
        try {
            userService.addRecipe(email, recipeId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Recipe added to user favorites");
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not add recipe to user's favorites" + e.getMessage());
        }
    }

    // delete a recipe from a users favorites
    @DeleteMapping("/deleterecipe/{email}/{recipeId}")
    public boolean deleteRecipe(@PathVariable String email, @PathVariable String recipeId) {
        return userService.deleteRecipe(email, recipeId);
    }

    // get a users favorites
    @GetMapping("/getuserfavorites/{email}")
    public Set<RecipeEntity> getUserFavorites(@PathVariable String email) {
        return userService.getUserFavorites(email);
    }

    @GetMapping("/getgrocerylist/{email}")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@PathVariable String email) {
        return userService.getGroceryList(email);
    }

    @DeleteMapping("/removefromgrocerylist/{email}/{foodId}")
    public boolean removeFromGroceryList(@PathVariable String email, @PathVariable String foodId) {
        return userService.removeFromGroceryList(email, foodId);
    }
}
