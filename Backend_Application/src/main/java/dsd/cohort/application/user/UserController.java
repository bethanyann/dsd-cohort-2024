package dsd.cohort.application.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dsd.cohort.application.ingredient.IngredientDTO;
import dsd.cohort.application.ingredient.IngredientEntity;
import dsd.cohort.application.recipe.RecipeDTO;
import dsd.cohort.application.recipe.RecipeEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Create a new user", description = "Create a new user with the provided form data")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(responseCode = "500", description = "Could not create user",
            content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/createuser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserRegisterDTO user) {

        try {
            UserEntity newUser = userService.createUser(user);

            if (newUser == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newUser);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't Create User");
        }
    }

    @Operation(summary = "Add a recipe to a user's favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe added to favorites",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Recipe not added to favorites",
            content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/addrecipetofavorites")
    public ResponseEntity<String> addRecipe(@RequestBody UserDataRequestDTO userDataRequestDTO) {
        try {
            userService.addRecipe(userDataRequestDTO);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Recipe added to user favorites");
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not add recipe to user's favorites: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a recipe from a user's favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recipe deleted from favorites",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Recipe not deleted from favorites",
            content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/removerecipefromfavorites")
    public ResponseEntity<String> deleteRecipe(@RequestBody UserDataRequestDTO userDataRequestDTO) {
        try {
            userService.deleteRecipe(userDataRequestDTO.getEmail(), userDataRequestDTO.getId());

            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not remove from user's favorites: " + e.getMessage());
        }

    }

    @Operation(summary = "Get a user's favorites")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User's favorites returned", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecipeDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Could not get user's favorites",
            content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/getuserfavorites/{email}")
    public ResponseEntity<Set<RecipeEntity>> getUserFavorites(@PathVariable String email) {
        try {
            Set<RecipeEntity> userFavorites = userService.getUserFavorites(email);
            return ResponseEntity.status(HttpStatus.OK).body(userFavorites);

        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not get user's favorites.");
        }
    }

    @Operation(summary = "Add an ingredient to a user's grocery list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredient added to user's grocery list", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDTO.class))),
        @ApiResponse(responseCode = "500", description = "Could not add ingredient to user's grocery list.",
            content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/additemtogrocerylist")
    public ResponseEntity<String> addItemToGroceryList(@RequestBody UserDataRequestDTO userDataRequestDTO) {

        try {
            userService.addGroceryItem(userDataRequestDTO);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Ingredient added to user's grocery list");
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not add ingredient to user's grocery list: " + e.getMessage());
        }
    }

    @Operation(summary = "Get a user's grocery list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Users grocery list returned", 
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = IngredientDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Could not get user's grocery list",
            content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/getgrocerylist/{email}")
    public ResponseEntity<Set<IngredientEntity>> getGroceryList(@PathVariable String email) {
        try {
            Set<IngredientEntity> userGroceryList = userService.getGroceryList(email);
            return ResponseEntity.status(HttpStatus.OK).body(userGroceryList);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not get user's grocery list");
        }
    }

    @Operation(summary = "Remove an ingredient to a user's grocery list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ingredient removed from grocery list",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Ingredient not removed from grocery list",
            content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/removefromgrocerylist")
    public ResponseEntity<String> removeFromGroceryList(UserDataRequestDTO userDataRequestDTO) {
        try{
            userService.removeFromGroceryList(userDataRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Item removed from user's grocery list.");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not remove item " + userDataRequestDTO.getId() +
                    "from users grocery list.");
        }
    }

    @Operation(summary = "User authentication", description = "This endpoint authenticates a user, returns the user if authenticated or null if not")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User authenticated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))),
        @ApiResponse(responseCode = "500", description = "User not authenticated",
            content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/auth")
    public ResponseEntity<UserEntity> userauth(@RequestBody UserRequestDTO userRequestDTO) {

        UserEntity auth;
        try {
            auth = userService.userauth(userRequestDTO);
            if (auth != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(auth);
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
