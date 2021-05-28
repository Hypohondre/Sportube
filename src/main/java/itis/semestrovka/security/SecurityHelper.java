package itis.semestrovka.security;

import itis.semestrovka.models.User;
import itis.semestrovka.security.details.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityHelper {

    public UserDetailsImpl getSecurityPrincipal() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
