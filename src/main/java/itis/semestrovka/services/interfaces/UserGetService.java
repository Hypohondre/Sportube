package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;

import java.util.Optional;

public interface UserGetService {
    Optional<UserDto> getUser(String login);
}
