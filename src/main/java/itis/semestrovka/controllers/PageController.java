package itis.semestrovka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {

    @GetMapping("/lenta")
    public String getLentaPage() {
        return "users_view_page";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {return "sign_in_page";}

    @GetMapping()
    public String getPage() {return "";}

    @GetMapping("/progile")
    public String getProfilePage() {return "profile_page";}
}
