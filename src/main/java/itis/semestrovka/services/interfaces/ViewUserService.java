package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViewUserService {
    Page<UserDto> getUsers(Pageable pageable);

    UserDto getUser(Long id);

    UserDto createUser(SignUpForm form);

    UserDto updateUser(Long id, SignUpForm form);

    void deleteUser(Long id);
}
