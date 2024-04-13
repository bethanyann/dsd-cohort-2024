package dsd.cohort.application.users;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<UsersEntity> findUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        Optional <UsersEntity> user = usersRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public UsersEntity createUser(UsersEntity user) {
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
}
