package itis.semestrovka.controllers;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.services.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final LoginService login;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody SignInForm form, HttpServletResponse response, HttpServletRequest request) {
        List<Cookie> list = Arrays.stream(request.getCookies()).filter(x->x.getName().equals("token")).collect(Collectors.toList());
        if (!list.isEmpty()) {
            list.get(0).setMaxAge(-1);
        }
        TokenDto token = login.login(form);
        Cookie cookie = new Cookie("token", token.getToken());
        cookie.setMaxAge(60*60*24*356);
        response.addCookie(cookie);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
