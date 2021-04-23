package itis.semestrovka.controllers;

import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.models.JwtToken;
import itis.semestrovka.services.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final LoginService login;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody SignInForm form) {
        return new ResponseEntity<>(login.login(form), HttpStatus.OK);
    }
}
