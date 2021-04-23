package itis.semestrovka.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.models.JwtToken;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.TokenRepository;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("key")
    private String key;

    @SneakyThrows
    @Override
    public JwtToken login(SignInForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getLogin()).orElseThrow
                ((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(signInForm.getPassword(), user.getPassword())) {
            String tokenValue = JWT.create()
                    .withSubject(user.getId().toString())
                    .withClaim("email", user.getEmail())
                    .withClaim("role", user.getRole().toString())
                    .withClaim("state", user.getState().toString())
                    .withClaim("birth", user.getBirth().toString())
                    .withClaim("photo", user.getPhoto())
                    .withClaim("code", user.getCode())
                    .sign(Algorithm.HMAC256(key));
            JwtToken token = JwtToken.builder()
                    .value(tokenValue)
                    .user(user)
                    .build();
            tokenRepository.save(token);
            return token;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
