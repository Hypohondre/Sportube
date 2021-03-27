package itis.semestrovka.dto.mappers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "birth", source = "user.birth", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "role", source = "user.role"),
            @Mapping(target = "state", source = "user.state"),
            @Mapping(target = "photo", source = "user.photo")
    })
    UserDto userToDto(User user);

    @InheritInverseConfiguration
    User dtoToUser(UserDto userDto);
}