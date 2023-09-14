package petkovskimariobachelor.userservice.service.interfaces;

import org.springframework.http.ResponseEntity;
import petkovskimariobachelor.userservice.dtos.UserRequestDto;
import petkovskimariobachelor.userservice.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void register(UserRequestDto userRequestDto);
    Boolean passwordMatches(User user, String password);
    User findByEmail(String email);
    ResponseEntity<Map<String, String>> getUserId(String bearerToken);
    List<User> findAll();
}
