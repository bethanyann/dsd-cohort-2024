package dsd.cohort.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterDTO {
  
  private String firstName;
  private String lastName;
  private String email;
  private String password;

}
