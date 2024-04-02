package dsdcohort.Application.Profile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public String createProfile(@RequestBody Profile profile){
        var created = profileService.createProfile(profile);
        if(created){
            System.out.println("it works");
            return "Profile Created";
        }
        return "Something went wrong";
    }
}
