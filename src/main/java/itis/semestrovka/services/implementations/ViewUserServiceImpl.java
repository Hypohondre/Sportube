package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.dto.mappers.UserDtoMapper;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ViewUserServiceImpl implements ViewUserService {
    private final UserRepository userRepository;
    private final UserDtoMapper mapper;

    @Override
    public Page<UserDto> getUsers(int number) {
        Pageable pageable = PageRequest.of(number, 2);
        Page<User> page = userRepository.findAll(pageable);

        return page.map(mapper::userToDto);
    }

    @Override
    public UserDto createUser(SignUpForm form) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, SignUpForm form) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
