package petkovskimariobachelor.userservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.userservice.dtos.UserRequestDto;
import petkovskimariobachelor.userservice.service.interfaces.AuthService;
import petkovskimariobachelor.userservice.service.interfaces.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    @PostMapping("/register")
    private void register(@RequestBody UserRequestDto userRequestDto){
        this.userService.register(userRequestDto);
    }
    @PostMapping("/login")
    public String token(Authentication authentication){
        String token = this.authService.generateToken(authentication);
        return token;
    }
    @GetMapping("/test")
    private String test(){
        return "test";
    }
}
