package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.services.implementations.SignUpServiceImpl;
import itis.semestrovka.services.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpServiceImpl signUpService;
    private final MailService mailService;

    @GetMapping
    public String getSignUpPage(
            @RequestParam(value = "errors", required = false) List<String> errors,
            Model model) {

        model.addAttribute("errors", errors);
        return "sign_up_page";
    }

    @PostMapping
    public String signUp(
            @Valid SignUpForm signUpForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            List<String> errorsList = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectAttributes.addAttribute("errors", errorsList);
            return "redirect:/signUp";
        } else {
            Optional<UserDto> userCandidate = signUpService.signUp(signUpForm);
            if(userCandidate.isPresent()) {
                UserDto user = userCandidate.get();
                mailService.sendMail(user);

                return "redirect:/signIn";
            } else {
                redirectAttributes.addAttribute("errors", Collections.singletonList("This email have already existed!"));
                return "redirect:/signUp";
            }
        }
    }

}
