package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> getUser(String login) {
        Optional<User> userCandidate = userRepository.findByEmail(login);

        if(userCandidate.isPresent()) {
            return Optional.ofNullable(UserDto.fromUser(userCandidate.get()));
        } else {
            return Optional.empty();
        }
    }
}
