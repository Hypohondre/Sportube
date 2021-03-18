package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.services.implementations.UserGetServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ViewProfileController {
    private final UserGetServiceImpl userGetService;

    @GetMapping
    public String getProfilePage(
            @RequestParam String email,
            Model model) {
        Optional<UserDto> userCandidate = userGetService.getUser(email);

        if(userCandidate.isPresent()) {
            model.addAttribute("user", userCandidate.get());
            return "profile_page";
        } else {
            return "redirect:/signIn";
        }
    }
}