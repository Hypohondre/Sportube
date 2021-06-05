package itis.semestrovka.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import itis.semestrovka.services.interfaces.SmsConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequiredArgsConstructor
public class SmsSendController {

    private final SmsConfirmService smsSender;

    @PreAuthorize("permitAll()")
    @GetMapping("/sendSms")
    public String sendSmsMessage(@RequestParam String phone, @RequestParam String text) {
        return smsSender.sendSms(phone, text);
    }
}