package itis.semestrovka.controllers;

import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Hidden;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.security.SecurityHelper;
import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.security.token.TokenAuthentication;
import itis.semestrovka.services.interfaces.MailService;
import itis.semestrovka.services.interfaces.ProfileService;
import itis.semestrovka.services.interfaces.UploadImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import java.io.IOException;

@Hidden
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;
    private final MailService mailService;
    private final SecurityHelper helper;
    private final UploadImgService imgService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getProfile")
    public UserDto getProfile(@CookieValue Cookie token) {
        return service.getUser(token.getValue());
    }

    @PreAuthorize("@UUDS.updateUserDetails(principal, authentication)")
    @GetMapping("/sendMail")
    public RedirectView sendMail(TokenAuthentication tokenAuthentication) throws IOException, TemplateException {
        UserDetailsImpl userDetails = (UserDetailsImpl) tokenAuthentication.getDetails();
        mailService.sendUserMail(userDetails.getUser());
        return new RedirectView("/profile");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/changePhoto")
    public RedirectView changePhoto(@RequestParam("file") MultipartFile photo) throws Throwable {
        UserDetailsImpl userDetails = helper.getSecurityPrincipal();
        String photoPath;
        if (photo != null) {
            photoPath = imgService.upload(photo);
            service.changePhoto(photoPath, userDetails.getUser().getId());
        }
        return new RedirectView("/profile");
    }
}
