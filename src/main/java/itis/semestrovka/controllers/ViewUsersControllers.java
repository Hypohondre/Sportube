package itis.semestrovka.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itis.semestrovka.services.interfaces.ViewUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
public class ViewUsersControllers {
    private final ViewUserService viewUserService;

    @GetMapping
    public String list(Model model) {

        model.addAttribute("users", viewUserService.getUsers(0));
        return "users_view_page";
    }

    @PostMapping
    public ResponseEntity<String> addUsers(
            @RequestParam String number
    ) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(viewUserService.getUsers(Integer.parseInt(number)));

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
