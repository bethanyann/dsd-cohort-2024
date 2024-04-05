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
        return null;
    }

    @Override
    public boolean userExists(String email) {
        return false;
    }

    @Override
    public boolean createUser(UserEntity user) {
        return false;
    }
}
