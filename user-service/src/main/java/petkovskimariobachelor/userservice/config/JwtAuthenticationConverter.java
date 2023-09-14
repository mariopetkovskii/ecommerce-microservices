package petkovskimariobachelor.userservice.config;

import petkovskimariobachelor.userservice.entity.User;
import petkovskimariobachelor.userservice.exceptions.UserNotExistException;
import petkovskimariobachelor.userservice.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final UserRepository userRepository;
    public JwtAuthenticationConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        String email = source.getSubject();
        List<GrantedAuthority> authorities = Optional.ofNullable(source.getClaimAsStringList("roles"))
                .map(roles -> {
                    List<GrantedAuthority> grantedAuthorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                    return grantedAuthorities;
                })
                .orElse(Collections.emptyList());
        User user = this.userRepository.findByEmailOptional(email).orElseThrow(UserNotExistException::new);
        return new JwtAuthenticationToken(source, authorities, user.getEmail());
    }
}