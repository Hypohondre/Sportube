package itis.semestrovka.services.implementations;

import freemarker.template.TemplateException;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;
import itis.semestrovka.services.interfaces.MailService;
import itis.semestrovka.services.interfaces.SenderService;
import itis.semestrovka.services.interfaces.TemplateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final TemplateResolver templateResolver;
    private final SenderService senderService;

    @Value("${server.main.address}")
    private String serverBasicAddress;

    @Override
    public void sendMail(UserDto userDto) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", userDto.getUsername());
        parameters.put("link", serverBasicAddress + "confirm/" + userDto.getCode());
        String html = templateResolver.process("mail.ftl", parameters);
        senderService.sendMessage("Confirm your registration", userDto.getEmail(), html);
    }

    @Override
    public void sendUserMail(User user) throws IOException, TemplateException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user", user);
        String html = templateResolver.process("user_mail.ftl", parameters);
        senderService.sendMessage("lovi usera", user.getEmail(), html);
    }

}
