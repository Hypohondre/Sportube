package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.TokenDto;
import itis.semestrovka.dto.forms.SignInForm;

public interface LoginService {
    TokenDto login(SignInForm signInForm);
}
