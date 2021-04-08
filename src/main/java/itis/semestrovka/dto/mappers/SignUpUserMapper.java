package itis.semestrovka.dto.mappers;

import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.models.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SignUpUserMapper {
    @Mappings({
            @Mapping(target = "email", source = "form.email"),
            @Mapping(target = "password", source = "form.password"),
            @Mapping(target = "username", source = "form.username")
    })
    User signUpToUser(SignUpForm form);

    @InheritInverseConfiguration
    SignUpForm userToSignUp(User user);
}
