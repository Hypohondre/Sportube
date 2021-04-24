package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import org.springframework.data.domain.Page;

public interface ViewUserService {
    Page<UserDto> getUsers(int number);

    UserDto getUser(Long id);

    UserDto createUser(SignUpForm form);

    UserDto updateUser(Long id, SignUpForm form);

    void deleteUser(Long id);
}
