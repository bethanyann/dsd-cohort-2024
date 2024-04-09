package dsd.cohort.application.user;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if(user != null){
            return user;
        }
        return null;
    }

    @Override
    public boolean userExists(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        var newUser = userRepository.save(user);
        if(newUser != null){
            return newUser;
        }
        return null;
    }
}
