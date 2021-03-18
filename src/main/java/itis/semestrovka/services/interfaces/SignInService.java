package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignInForm;

import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(SignInForm form);
}
