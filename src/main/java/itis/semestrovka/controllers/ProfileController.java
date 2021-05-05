package itis.semestrovka.controllers;

import freemarker.template.TemplateException;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.services.interfaces.MailService;
import itis.semestrovka.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;
    private final MailService mailService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getProfile")
    public UserDto getProfile(@CookieValue Cookie token) {
        return service.getUser(token.getValue());
    }

    @PreAuthorize("@UUDS.updateUserDetails(principal, authentication)")
    @GetMapping("/sendMail")
    public void sendMail(@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException, TemplateException {
        mailService.sendUserMail(userDetails.getUser());
    }
}
