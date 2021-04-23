package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ViewUsersControllers {
    private final ViewUserService viewUserService;

    @GetMapping(value = {"/users", "/users/{id}"})
    public Page<UserDto> getUsers(@PathVariable Optional<String> id) {
        return viewUserService.getUsers(Integer.parseInt(id.orElse("0")));
    }
}
