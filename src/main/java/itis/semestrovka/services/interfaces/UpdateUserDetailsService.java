package itis.semestrovka.services.interfaces;

import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.security.token.TokenAuthentication;

public interface UpdateUserDetailsService {
    boolean updateUserDetails(UserDetailsImpl userDetails, TokenAuthentication authentication);
}
