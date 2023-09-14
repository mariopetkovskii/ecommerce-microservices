package petkovskimariobachelor.userservice.dtos;

import lombok.Getter;

@Getter
public class UserRequestDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
}