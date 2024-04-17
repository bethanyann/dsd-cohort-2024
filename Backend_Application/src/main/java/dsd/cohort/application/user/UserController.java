package dsd.cohort.application.user;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All users returned"),
        @ApiResponse(responseCode = "500", description = "Could not create user")
    })
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

    @Operation(summary = "Get a user by email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "500", description = "User not found")
    })
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

    @Operation(summary = "Add a recipe to a users favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe added to favorites"),
        @ApiResponse(responseCode = "500", description = "Recipe not added to favorites")
    })
    // add a recipe to a users favorites
    @GetMapping("/addrecipe/{email}/{recipeId}")
    public boolean addRecipe(@PathVariable String email, @PathVariable String recipeId) {
        return userService.addRecipe(email, recipeId);
    }

    @Operation(summary = "Delete a recipe from a users favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe deleted from favorites"),
        @ApiResponse(responseCode = "500", description = "Recipe not deleted from favorites")
    })
    // delete a recipe from a users favorites
    @DeleteMapping("/deleterecipe/{email}/{recipeId}")
    public boolean deleteRecipe(@PathVariable String email, @PathVariable String recipeId) {
        return userService.deleteRecipe(email, recipeId);
    }

    @Operation(summary = "Get a users favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users favorites returned"),
        @ApiResponse(responseCode = "500", description = "Could not get users favorites")
    })
    // get a users favorites
    @GetMapping("/getuserfavorites/{email}")
    public Set<RecipeEntity> getUserFavorites(@PathVariable String email) {
        return userService.getUserFavorites(email);
    }

    @Operation(summary = "Get a users grocery list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users grocery list returned"),
        @ApiResponse(responseCode = "500", description = "Could not get users grocery list")
    })
    @GetMapping("/getgrocerylist/{email}")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@PathVariable String email) {
        return userService.getGroceryList(email);
    }

    @Operation(summary = "Remove an ingredient to a users grocery list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredient removed from grocery list"),
        @ApiResponse(responseCode = "500", description = "Ingredient not removed from grocery list")
    })
    @DeleteMapping("/removefromgrocerylist/{email}/{foodId}")
    public boolean removeFromGroceryList(@PathVariable String email, @PathVariable String foodId) {
        return userService.removeFromGroceryList(email, foodId);
    }
}
