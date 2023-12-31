package petkovskimariobachelor.userservice.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import petkovskimariobachelor.commonservice.shareddtos.TokenValidationResponse;

import java.util.Map;

public interface AuthService {
    String generateToken(Authentication authentication);
    ResponseEntity<Map<String, String>> getUserId();
    TokenValidationResponse validateToken();
    Boolean validateAdminToken();
}
