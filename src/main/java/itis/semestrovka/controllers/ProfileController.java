package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getProfile")
    public UserDto getProfile(@CookieValue Cookie token) {
        return service.getUser(token.getValue());
    }
}
