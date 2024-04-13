package dsd.cohort.application.user;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface UserService {

    UserEntity findUserByEmail(String email);

    boolean userExists(String email);

    UserEntity createUser(UserEntity user);

    boolean addRecipe(String email, String recipeId);

    boolean deleteRecipe(String email, String recipeId);

    List<UserEntity> getAll();
}
