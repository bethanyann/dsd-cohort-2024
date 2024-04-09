package dsd.cohort.application.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createuser")
    public UserEntity createUser(@RequestBody UserEntity user){
        var userCreated = userService.createUser(user);
        if(userCreated != null){
        return userCreated;
        }
        return null;
    }

    @GetMapping("/getuserbyemail")
    public UserEntity getUserByEmail(@RequestBody String userEmail){
        return userService.getUserByEmail(userEmail);
    }

    @GetMapping("/userexist")
    public boolean userExist(@RequestBody String userEmail){
        return userService.userExists(userEmail);

    }
}
