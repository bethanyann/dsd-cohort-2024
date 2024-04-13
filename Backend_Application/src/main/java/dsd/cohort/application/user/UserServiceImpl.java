package dsd.cohort.application.user;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository usersRepository;

    public UserServiceImpl(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        Optional <UserEntity> user = usersRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        var newUser = usersRepository.save(user);
        if(newUser != null){
            return newUser;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Override
    public boolean addRecipe(String email, String recipe) {

        UserEntity user = usersRepository.findByEmail(email).orElse(null);

        if (user != null) {
            user.getFavoriteRecipes().add(recipe);
            usersRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteRecipe(String email, String recipe) {

        UserEntity user = usersRepository.findByEmail(email).orElse(null);

        if (user != null) {

            if (!user.getFavoriteRecipes().contains(recipe)) {
                return false;
            }

            user.getFavoriteRecipes().remove(recipe);
            usersRepository.save(user);
            return true;
        }

        return false;
    }
}
