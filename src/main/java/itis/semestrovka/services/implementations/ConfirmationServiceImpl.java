package itis.semestrovka.services.implementations;

import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    private final UserRepository userRepository;

    @Override
    public boolean confirmByCode(String code) {
        Optional<User> userCandidate = userRepository.findByCode(code);

        if(userCandidate.isPresent()) {
            User user = userCandidate.get();

            user.setState(User.State.FULLACTIVE);
            userRepository.save(user);
        }
        return userCandidate.isPresent();
    }
}
