package itis.semestrovka.controllers;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.services.interfaces.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {
    private final SignInService signInService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody SignInForm form) {
        return new ResponseEntity<>(signInService.login(form), HttpStatus.OK);
    }
}
