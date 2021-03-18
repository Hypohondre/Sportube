package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;

import java.util.Optional;

public interface SignUpService {
    Optional<UserDto> signUp(SignUpForm form);
}
