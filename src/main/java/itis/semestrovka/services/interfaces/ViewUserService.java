package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;

import java.util.List;

public interface ViewUserService {
    List<UserDto> getUsers(int number);
}
