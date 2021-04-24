package itis.semestrovka.services.implementations;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.dto.mappers.SignUpUserMapper;
import itis.semestrovka.dto.mappers.UserDtoMapper;
import itis.semestrovka.models.Playlist;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ViewUserServiceImpl implements ViewUserService {
    private final UserRepository userRepository;
    private final UserDtoMapper dtoMapper;
    private final SignUpUserMapper formMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> getUsers(int number) {
        Pageable pageable = PageRequest.of(number, 2);
        Page<User> page = userRepository.findAll(pageable);

        return page.map(dtoMapper::userToDto);
    }

    @Override
    public UserDto createUser(SignUpForm form) {
        User user = formMapper.signUpToUser(form);
        user.setRole(User.Role.USER);
        user.setState(User.State.ACTIVE);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setPhoto("default.png");
        user.setCode(UUID.randomUUID().toString());
        user.setBirth(Date.valueOf(form.getBirth()));
        user.getPlaylists().add(Playlist.builder()
                .name(user.getUsername())
                .user(user)
                .build());
        userRepository.save(user);
        return dtoMapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(Long id, SignUpForm form) {
        User userForUpdate = userRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        userForUpdate.setUsername(form.getUsername());
        userForUpdate.setEmail(form.getEmail());
        userForUpdate.setBirth(Date.valueOf(form.getBirth()));

        userRepository.save(userForUpdate);
        return dtoMapper.userToDto(userForUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        User userForDelete = userRepository.findById(id)
                .orElseThrow(IllegalStateException::new);

        userRepository.delete(userForDelete);
    }
}
