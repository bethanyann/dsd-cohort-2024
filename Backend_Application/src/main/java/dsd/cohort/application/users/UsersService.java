package dsd.cohort.application.users;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsersService {


    Optional<UsersEntity> findUserByEmail(String email);

    boolean userExists(String email);

    UsersEntity createUser(UsersEntity user);
}
