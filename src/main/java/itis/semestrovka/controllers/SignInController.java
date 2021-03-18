package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.services.implementations.SignInServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {
    //private final SignInServiceImpl signInService;
//@RequestParam(required = false) String error,
//            Model model
    @GetMapping
    public String getSignInPage() {
       // model.addAttribute("error", error);
        return "sign_in_page";
    }

//    @PostMapping
//    public String signIn(
//            SignInForm form,
//            RedirectAttributes redirectAttributes) {
//
//        Optional<UserDto> userCandidate =  signInService.signIn(form);
//
//        if(userCandidate.isPresent()) {
//            redirectAttributes.addAttribute("email", form.getLogin());
//            return "redirect:/profile";
//        } else {
//            redirectAttributes.addAttribute("error", "Incorrect login or password");
//            return "redirect:/signIn";
//        }
//    }
}
