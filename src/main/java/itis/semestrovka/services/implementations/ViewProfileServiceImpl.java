package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ViewProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewProfileServiceImpl implements ViewProfileService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> getUser() {

        return null;
    }
}
