package itis.semestrovka.services.interfaces;

import freemarker.template.TemplateException;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;

import java.io.IOException;

public interface MailService {
    void sendMail(UserDto dto);
    void sendUserMail(User user) throws IOException, TemplateException;
}
