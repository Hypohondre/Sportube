package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.services.interfaces.UploadImgService;
import itis.semestrovka.services.interfaces.ViewProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

//@Controller
@RequiredArgsConstructor
//@RequestMapping("/profile")
public class ViewProfileController {
    private final UploadImgService uploadImgService;
    private final ViewProfileService viewProfileService;

    @GetMapping
    public String getProfilePage(
            @AuthenticationPrincipal UserDetailsImpl user,
            Model model) {

        Optional<UserDto> userCandidate = viewProfileService.getUser(user);

        if (userCandidate.isPresent()) {
            model.addAttribute("user", userCandidate.get());
            return "profile_page";
        } else {
            return "redirect:/signIn";
        }
    }

    @PostMapping
    public String addPhoto(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal UserDetailsImpl user
            ) throws IOException {

        String photo = uploadImgService.upload(file);
        Optional<UserDto> userCandidate = viewProfileService.getUser(user);

        if(userCandidate.isPresent()) {
            viewProfileService.updateUserPhoto(photo, user.getUser());
        }

        return "redirect:/profile";
    }
}