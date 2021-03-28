package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.services.interfaces.MailService;
import itis.semestrovka.services.interfaces.TemplateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import itis.semestrovka.services.interfaces.SenderService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TemplateProcessor templateProcessor;
    private final SenderService senderService;

    @Value("${server.main.address}")
    private String serverBasicAddress;

    @Override
    public void sendMail(UserDto userDto) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", userDto.getUsername());
        parameters.put("link", serverBasicAddress + "confirm/" + userDto.getCode());
        sendMail(parameters, "mail.ftl", userDto.getEmail(), "Confirm your registration");
    }


    private void sendMail(Map<String, String> parameters, String template, String email, String subject) {
        String html = templateProcessor.getProcessedTemplate(parameters, template);
        senderService.sendMessage(subject, email, html);
    }

}
