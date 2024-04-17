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

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/test")
    public List<UserEntity> test() {
        return userServiceImpl.getAll();
    }

    @PostMapping("/createuser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){

        try {
            UserEntity newUser = userServiceImpl.createUser(user);
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
            UserEntity user = userServiceImpl.findUserByEmail(email);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(user);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not found");
        }
    }

    // add a recipe to a users favorites
    @GetMapping("/addrecipe")
    public boolean addRecipe(@RequestBody String email, @RequestBody String recipeId) {
        return userServiceImpl.addRecipe(email, recipeId);
    }

    // delete a recipe from a users favorites
    @DeleteMapping("/deleterecipe")
    public boolean deleteRecipe(@RequestBody String email, @RequestBody String recipeId) {
        return userServiceImpl.deleteRecipe(email, recipeId);
    }

    // get a users favorites
    @GetMapping("/getuserfavorites")
    public Set<RecipeEntity> getUserFavorites(@RequestBody String email) {
        return userServiceImpl.getUserFavorites(email);
    }

    @GetMapping("/getgrocerylist")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@RequestBody String email) {
        return userServiceImpl.getGroceryList(email);
    }

    @DeleteMapping("/removefromgrocerylist")
    public boolean removeFromGroceryList(@RequestBody String email, @RequestBody String foodId) {
        return userServiceImpl.removeFromGroceryList(email, foodId);
    }
}
