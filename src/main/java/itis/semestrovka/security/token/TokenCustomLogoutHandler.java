package itis.semestrovka.security.token;

import itis.semestrovka.models.JwtToken;
import itis.semestrovka.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class TokenCustomLogoutHandler implements LogoutHandler {
    private final TokenRepository repository;

    @Override
    @SneakyThrows
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        JwtToken token = repository.findByValue(authentication.getName())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("token not found"));
        repository.delete(token);
    }
}
