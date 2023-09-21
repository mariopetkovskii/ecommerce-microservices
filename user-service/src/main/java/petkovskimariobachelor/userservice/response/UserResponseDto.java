package petkovskimariobachelor.userservice.response;

import java.io.Serializable;

public record UserResponseDto(String id, String firstName, String lastName, String email, String role) {
}
