package dsd.cohort.application.user;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @PostMapping("/createusers")
    public UserEntity createUser(@RequestBody UserEntity user){
        UserEntity userCreated = userService.createUser(user);
        if(userCreated != null){
        return userCreated;
        }
        return null;
    }

    @GetMapping("/finduserbyemail")
    public Optional<UserEntity> findUserByEmail(@RequestBody String userEmail){
        return userService.findUserByEmail(userEmail);
    }

    @GetMapping("/addRecipe")
    public boolean addRecipe(@RequestBody String email, @RequestBody String recipe){
        return userService.addRecipe(email, recipe);
    }

    @DeleteMapping("/deleteRecipe")
    public boolean deleteRecipe(@RequestBody String email, @RequestBody String recipe){
        return false;
    }
}
