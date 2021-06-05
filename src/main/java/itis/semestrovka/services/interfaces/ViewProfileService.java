package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;
import itis.semestrovka.security.details.UserDetailsImpl;

import java.util.Optional;

public interface ViewProfileService {
    Optional<UserDto> getUser(UserDetailsImpl user);
    void updateUserPhoto(String photoName, User user);
}
