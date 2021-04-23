package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import org.springframework.data.domain.Page;

public interface ViewUserService {
    Page<UserDto> getUsers(int number);
}
