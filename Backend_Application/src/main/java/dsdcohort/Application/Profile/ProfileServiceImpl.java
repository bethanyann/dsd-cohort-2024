package dsdcohort.Application.Profile;

import org.springframework.stereotype.Service;
import dsdcohort.Application.Profile.*;

@Service
public class ProfileServiceImpl implements ProfileService{
    
    private ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Boolean createProfile(Profile profile) {

        try {
            profileRepository.save(profile);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}