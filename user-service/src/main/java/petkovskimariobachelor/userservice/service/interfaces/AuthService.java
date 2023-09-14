package petkovskimariobachelor.userservice.service.interfaces;

import org.springframework.security.core.Authentication;

public interface AuthService {
    String generateToken(Authentication authentication);
}
