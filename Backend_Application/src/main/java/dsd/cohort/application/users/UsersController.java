package dsd.cohort.application.users;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/createusers")
    public UsersEntity createUser(@RequestBody UsersEntity user){
        UsersEntity userCreated = usersService.createUser(user);
        if(userCreated != null){
        return userCreated;
        }
        return null;
    }

    @GetMapping("/finduserbyemail")
    public Optional<UsersEntity> findUserByEmail(@RequestBody String userEmail){
        return usersService.findUserByEmail(userEmail);
    }

    @GetMapping("/userexist")
    public boolean userExist(@RequestBody String userEmail){
        return usersService.userExists(userEmail);

    }
}
