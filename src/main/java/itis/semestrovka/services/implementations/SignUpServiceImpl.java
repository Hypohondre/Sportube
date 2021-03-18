package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static itis.semestrovka.models.User.fromSignUpToUser;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl  implements SignUpService {
    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> signUp(SignUpForm form) {
        if (userRepository.existsByEmail(form.getEmail())) return null;

        User user = fromSignUpToUser(form);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userRepository.save(user);

        return Optional.ofNullable(UserDto.fromUser(user));
    }
}
