package itis.semestrovka.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.mappers.UserDtoMapper;
import itis.semestrovka.models.User;
import itis.semestrovka.repositories.UserRepository;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewUserServiceImpl implements ViewUserService {
    private final UserRepository userRepository;
    private UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);

    @Override
    public List<UserDto> getUsers(int number) {
        Pageable pageable = PageRequest.of(number, 2);
        Page<User> page = userRepository.findAll(pageable);

        List<UserDto> usersDto = page.get().collect(Collectors.toList())
                .stream()
                .map(value -> mapper.userToDto(value))
                .collect(Collectors.toList());

        return usersDto;
    }
}
