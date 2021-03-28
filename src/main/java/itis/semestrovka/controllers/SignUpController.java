package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.services.implementations.SignUpServiceImpl;
import itis.semestrovka.services.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        model.addAttribute("error", error);
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
            redirectAttributes.addAttribute("error", errorsList.toString());
            return "redirect:/signUp";
        } else {
            Optional<UserDto> userCandidate = signUpService.signUp(signUpForm);
            if(userCandidate.isPresent()) {
                UserDto user = userCandidate.get();
                mailService.sendMail(user);

                return "redirect:/signIn";
            } else {
                redirectAttributes.addAttribute("error", "This email have already existed!");
                return "redirect:/signUp";
            }
        }
    }

}
