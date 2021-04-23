package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.models.Token;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.TokenRepository;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public TokenDto login(SignInForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getLogin()).orElseThrow
                ((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(signInForm.getPassword(), user.getPassword())) {
            String tokenValue = UUID.randomUUID().toString();
            Token token = Token.builder()
                    .value(tokenValue)
                    .user(user)
                    .build();
            tokenRepository.save(token);
            return TokenDto.builder()
                    .token(tokenValue)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
