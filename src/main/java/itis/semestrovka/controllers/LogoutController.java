package itis.semestrovka.controllers;

import itis.semestrovka.models.JwtToken;
import itis.semestrovka.repositories.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LogoutController {
    private final TokenRepository repository;

    @GetMapping("/customLogout")
    public String logout(@CookieValue Cookie token, HttpServletResponse response){
        JwtToken jwt = repository.findByValue(token.getValue())
                .orElseThrow(IllegalStateException::new);
        repository.delete(jwt);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/signIn";
    }
}

