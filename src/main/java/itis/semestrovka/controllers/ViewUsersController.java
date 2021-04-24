package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ViewUsersController {
    private final ViewUserService viewUserService;

    @GetMapping("/users")
    public Page<UserDto> getUsers(@PathVariable Optional<String> id) {
        return viewUserService.getUsers(Integer.parseInt(id.orElse("0")));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(viewUserService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUsers(@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.createUser(form), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUsers(@PathVariable Long id,@RequestBody SignUpForm form) {
        return new ResponseEntity<>(viewUserService.updateUser(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
        viewUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
