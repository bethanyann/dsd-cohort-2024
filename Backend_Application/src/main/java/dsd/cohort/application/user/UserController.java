package dsd.cohort.application.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/addRecipe")
    public boolean addRecipe(@RequestBody String email, @RequestBody String recipeId){
        return userService.addRecipe(email, recipeId);
    }

    @DeleteMapping("/deleteRecipe")
    public boolean deleteRecipe(@RequestBody String email, @RequestBody String recipeId){
        return userService.deleteRecipe(email, recipeId);
    }
}
