package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.mappers.UserDtoMapper;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.security.details.UserDetailsImpl;
import itis.semestrovka.services.interfaces.ViewProfileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewProfileServiceImpl implements ViewProfileService {
    private final UserDtoMapper mapper;
    private final UserRepository repository;

    @Override
    public Optional<UserDto> getUser(UserDetailsImpl user) {
        return Optional.ofNullable(mapper.userToDto(user.getUser()));
    }

    @Override
    public void updateUserPhoto(String photoName, User user) {
        user.setPhoto(photoName);
        repository.save(user);
    }
}
