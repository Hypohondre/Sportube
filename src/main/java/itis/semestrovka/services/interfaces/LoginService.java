package itis.semestrovka.services.interfaces;

import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.models.JwtToken;

public interface LoginService {
    JwtToken login(SignInForm signInForm);
}
