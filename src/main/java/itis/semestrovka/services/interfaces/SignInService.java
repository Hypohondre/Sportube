package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;

public interface SignInService {
    TokenDto login(SignInForm form);
}
