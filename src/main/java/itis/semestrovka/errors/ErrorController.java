package itis.semestrovka.errors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorController {

    @GetMapping("/error")
    public String getErrorPage(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null && !error.equals(""))model.addAttribute("error",error);
        else model.addAttribute("error","Unknown error");
        return "error";
    }
}
