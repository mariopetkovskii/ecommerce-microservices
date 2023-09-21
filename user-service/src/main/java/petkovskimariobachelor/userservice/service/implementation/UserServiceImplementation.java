package petkovskimariobachelor.userservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.userservice.dtos.UserRequestDto;
import petkovskimariobachelor.userservice.entity.Role;
import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.exceptions.PasswordDoNotMatchException;
import petkovskimariobachelor.userservice.exceptions.UserAlreadyExistsException;
import petkovskimariobachelor.userservice.mappers.UserResponseDtoMapper;
import petkovskimariobachelor.userservice.repository.UserRepository;
import petkovskimariobachelor.userservice.response.UserResponseDto;
import petkovskimariobachelor.userservice.service.interfaces.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserResponseDtoMapper userResponseDtoMapper;

    @Override
    public void register(UserRequestDto userRequestDto) {
        User user = this.userRepository.findByEmail(userRequestDto.getEmail());
        if(user != null){
            throw new UserAlreadyExistsException();
        }
        if(!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())){
            throw new PasswordDoNotMatchException();
        }
        User createUser = new User.Builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .isEnabled(true)
                .role(Role.ROLE_USER)
                .build();
        this.userRepository.save(createUser);
    }

    @Override
    public Boolean passwordMatches(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<Map<String, String>> getUserId(String bearerToken) {
        return null;
    }
    @Override
    public List<UserResponseDto> findAll() {
        return this.userRepository.findAll()
                .stream().map(userResponseDtoMapper)
                .collect(Collectors.toList());
    }
}
