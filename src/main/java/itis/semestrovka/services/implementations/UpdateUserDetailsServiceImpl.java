package itis.semestrovka.services.implementations;

import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.security.token.TokenAuthentication;
import itis.semestrovka.services.interfaces.UpdateUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service("UUDS")
@RequiredArgsConstructor
public class UpdateUserDetailsServiceImpl implements UpdateUserDetailsService {
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public boolean updateUserDetails(UserDetailsImpl userDetails, TokenAuthentication authentication) {
        if (userDetails == null) return false;
        User user = userRepository.findById(userDetails.getUser().getId())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("user not found"));
        userDetails.setUser(user);
        authentication.setUserDetails(userDetails);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}
