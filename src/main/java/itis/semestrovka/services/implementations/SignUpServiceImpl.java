package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.dto.mappers.SignUpUserMapper;
import itis.semestrovka.dto.mappers.UserDtoMapper;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.SignUpService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl  implements SignUpService {
    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SignUpUserMapper formMapper;
    private final UserDtoMapper dtoMapper;

    @Override
    public Optional<UserDto> signUp(SignUpForm form) {
        if (userRepository.existsByEmail(form.getEmail())) return Optional.empty();

        User user = formMapper.signUpToUser(form);
        user.setRole(User.Role.USER);
        user.setState(User.State.ACTIVE);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setPhoto("default.png");
        user.setCode(UUID.randomUUID().toString());
        user.setBirth(Date.valueOf(form.getBirth()));
        userRepository.save(user);

        UserDto userDto = dtoMapper.userToDto(user);

        return Optional.ofNullable(userDto);
    }
}
