package itis.semestrovka.controllers;

import itis.semestrovka.dto.forms.ChangePasswordForm;
import itis.semestrovka.dto.forms.PasswordChangeConfirmForm;
import itis.semestrovka.models.Sms;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.SmsRepository;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.security.SecurityHelper;
import itis.semestrovka.services.implementations.SmsConfirmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class ChangePasswordController {
    private final PasswordEncoder passwordEncoder;
    private final SecurityHelper helper;
    private final SmsConfirmServiceImpl smsConfirmService;
    private final SmsRepository repository;
    private final UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changePassword")
    public String getChangePasswordPage() {
        return "change_password_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/confirmPassword")
    public String getConfirmPasswordPage() {
        return "confirm_password_page";
    }

    @PreAuthorize("@UUDS.updateUserDetails(principal, authentication)")
    @PostMapping("/change")
    public String changePassword(ChangePasswordForm form) {
        if (!form.getNewPassword().equals(form.getRepeatNewPassword())) return "redirect:/profile";

        User user = helper.getSecurityPrincipal().getUser();
        if (passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            Random r = new Random();
            String randomNumber = String.format("%04d", r.nextInt(1001));
            smsConfirmService.sendSms(user.getPhone(), randomNumber);
            repository.save(Sms.builder().text(randomNumber).password(form.getNewPassword()).build());
            return "redirect:/confirmPassword";
        } else {
            return "redirect:/profile";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/confirm")
    public String confirmChanges(PasswordChangeConfirmForm form) {
        Optional<Sms> sms = repository.findByText(form.getCode());
        if (sms.isPresent()) {
            User user = helper.getSecurityPrincipal().getUser();
            user.setPassword(passwordEncoder.encode(sms.get().getPassword()));
            userRepository.save(user);
            repository.delete(sms.get());
            return "redirect:/logout";
        } else {
            return "redirect:/profile";
        }
    }
}
