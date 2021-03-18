package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignInForm;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final UserRepository userRepository;
    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDto> signIn(SignInForm form) {
        Optional<User> userCandidate = userRepository.findByEmail(form.getLogin());
        if(userCandidate.isPresent()) {
            User user = userCandidate.get();
            if(passwordEncoder.matches(form.getPassword(), user.getPassword())) {
                return Optional.ofNullable(UserDto.fromUser(user));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
