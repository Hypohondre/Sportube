package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;

import java.util.Optional;

public interface ViewProfileService {
    Optional<UserDto> getUser();
}
