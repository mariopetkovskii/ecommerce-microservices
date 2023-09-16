package petkovskimariobachelor.userservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.exceptions.UserNotExistException;
import petkovskimariobachelor.userservice.repository.UserRepository;
import petkovskimariobachelor.userservice.service.interfaces.AuthService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final JwtEncoder encoder;
    private final UserRepository userRepository;
    @Override
    public String generateToken(Authentication authentication) {
        var email = authentication.getName();
        User user = userRepository.findByEmailOptional(email)
                .orElseThrow(UserNotExistException::new);
        Instant now = Instant.now();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(email)
                .claim("roles", roles)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public ResponseEntity<Map<String, String>> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.userRepository.findByEmail(email);
        Map<String, String> response = new HashMap<>();
        response.put("userId", user.getId().getId());
        response.put("email", email);
        return ResponseEntity.ok(response);
    }
}
