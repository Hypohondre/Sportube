package itis.semestrovka.controllers;

import itis.semestrovka.dto.UserDto;
import itis.semestrovka.dto.forms.SignUpForm;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ViewUsersController {
    private final ViewUserService viewUserService;

    @PreAuthorize("permitAll()")
    @GetMapping("/users")
    public Page<UserDto> getUsers(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return viewUserService.getUsers(pageable);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return viewUserService.getUser(id);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/users")
    public UserDto createUsers(SignUpForm form) {
        return viewUserService.createUser(form);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/users/{id}")
    public RedirectView updateUsers(@PathVariable Long id, SignUpForm form) {
        viewUserService.updateUser(id, form);
        return new RedirectView("/logout");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long id) {
        viewUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
