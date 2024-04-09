package dsd.cohort.application.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {


    UserEntity getUserByEmail(String email);

    boolean userExists(String email);

    UserEntity createUser(UserEntity user);
}
