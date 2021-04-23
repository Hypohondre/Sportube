package itis.semestrovka.security.details;

import itis.semestrovka.models.Token;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.TokenRepository;
import itis.semestrovka.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Component("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private final TokenRepository tokenRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Token token = tokenRepository.findByValue(value).orElseThrow((Supplier<Throwable>) () ->
        new UsernameNotFoundException("Token not found"));
        return new UserDetailsImpl(token.getUser());
    }
}
