package petkovskimariobachelor.userservice.mappers;

import org.springframework.stereotype.Service;
import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.response.UserResponseDto;

import java.util.function.Function;

@Service
public class UserResponseDtoMapper implements Function<User, UserResponseDto> {
    @Override
    public UserResponseDto apply(User user) {
        return new UserResponseDto(
                user.getId().getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getAuthority()
        );
    }
}
