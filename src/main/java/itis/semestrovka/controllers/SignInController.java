package itis.semestrovka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {
    @GetMapping
    public String getSignInPage() {return "sign_in_page";}
}
