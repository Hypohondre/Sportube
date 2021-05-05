package itis.semestrovka.controllers;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.services.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class SignInController {
    private final LoginService login;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public String login(SignInForm form, HttpServletResponse response) {
        TokenDto newToken = login.login(form);
        Cookie cookie = new Cookie("token", newToken.getToken());
        cookie.setMaxAge(60*60*24*356);
        response.addCookie(cookie);
        return "redirect:/profile";
    }
}
