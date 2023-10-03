package petkovskimariobachelor.userservice.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import petkovskimariobachelor.userservice.entity.Role;
import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        if (this.userRepository.findByEmailOptional("admin@ecommerce.com").isEmpty()) {
            User user = new User.Builder()
                    .firstName("Mario")
                    .lastName("Petkovski")
                    .email("admin@ecommerce.com")
                    .isEnabled(true)
                    .role(Role.ROLE_ADMIN)
                    .password(passwordEncoder.encode("admin"))
                    .build();
            this.userRepository.save(user);
        }

    }
}
