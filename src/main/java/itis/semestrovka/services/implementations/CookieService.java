package itis.semestrovka.services.implementations;

import itis.semestrovka.repositories.UserCookieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@RequiredArgsConstructor
public class CookieService {
    private final UserCookieRepository userCookieRepository;

    public boolean check() {
        return false;
    }

    public Cookie create() {
        return null;
    }
}
