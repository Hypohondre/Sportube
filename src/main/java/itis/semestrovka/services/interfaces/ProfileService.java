package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;

public interface ProfileService {
    UserDto getUser(String jwt);
    void changePhoto(String photoPath, Long id) throws Throwable;
}
