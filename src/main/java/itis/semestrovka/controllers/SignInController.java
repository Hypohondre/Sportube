package itis.semestrovka.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
