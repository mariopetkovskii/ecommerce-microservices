package petkovskimariobachelor.userservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.commonservice.shareddtos.TokenValidationResponse;
import petkovskimariobachelor.userservice.dtos.UserRequestDto;
import petkovskimariobachelor.userservice.response.UserResponseDto;
import petkovskimariobachelor.userservice.service.interfaces.AuthService;
import petkovskimariobachelor.userservice.service.interfaces.UserService;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/admin/test")
    private String test(){
        return "test";
    }
    @GetMapping("/getAll")

    private List<UserResponseDto> findAll(){
        return this.userService.findAll();
    }

    @PostMapping("/user-id")
    private ResponseEntity<Map<String, String>> getUserId(){
        return this.authService.getUserId();
    }

    @PostMapping("/validateToken")
    private TokenValidationResponse validateToken(@RequestHeader("Authorization") String token){
        return this.authService.validateToken(token);
    }

    @PostMapping("/admin/validateToken")
    private Boolean validateTokenAdmin(@RequestHeader("Authorization") String token){
        return this.authService.validateAdminToken(token);
    }
}
