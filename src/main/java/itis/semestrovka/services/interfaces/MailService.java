package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;

public interface MailService {
    void sendMail(UserDto dto);
}
