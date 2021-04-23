package itis.semestrovka.security.details;

import itis.semestrovka.models.JwtToken;
import itis.semestrovka.repositories.TokenRepository;
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
        JwtToken token = tokenRepository.findByValue(value).orElseThrow((Supplier<Throwable>) () ->
        new UsernameNotFoundException("Token not found"));
        return new UserDetailsImpl(token.getUser());
    }
}
