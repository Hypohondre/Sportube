package itis.semestrovka.security.details;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Date;

@RequiredArgsConstructor
@Component("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private final TokenRepository tokenRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        DecodedJWT jwt = JWT.decode(value);
        return new UserDetailsImpl(User.builder()
                .id(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .username(jwt.getClaim("username").asString())
                .role(User.Role.valueOf(jwt.getClaim("role").asString()))
                .state(User.State.valueOf(jwt.getClaim("state").asString()))
                .birth(Date.valueOf(jwt.getClaim("birth").asString()))
                .photo(jwt.getClaim("photo").asString())
                .phone(jwt.getClaim("phone").asString())
                .build());
    }
}
