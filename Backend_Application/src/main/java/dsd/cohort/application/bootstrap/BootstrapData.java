package dsd.cohort.application.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dsd.cohort.application.user.UserEntity;
import dsd.cohort.application.user.UserRepository;

@Component
public class BootstrapData implements CommandLineRunner {
  
  private final UserRepository userRepository;
  
  public BootstrapData(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    
    if (userRepository.findAll().isEmpty()) {
      UserEntity user = new UserEntity();
      user.setFirstName("Johnny");
      user.setLastName("Test");
      user.setEmail("test@test.com");
      user.setPassword("testPassword");
      userRepository.save(user);

      user = new UserEntity();
      user.setFirstName("Jane");
      user.setLastName("Test");
      user.setEmail("test2@test.com");
      user.setPassword("testPassword");
      userRepository.save(user);


      System.out.println("Bootstrap Data Loaded");
    }
  }
}
