package itis.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpForm {
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password must contain 8 or more characters")
    private String password;
    @Pattern(regexp = "^(?!.*\\.\\.)(?!\\.)(?!.*\\.$)(?!\\d+$)[a-zA-Z0-9.]{5,50}$",
    message = "Incorrect username")
    private String username;
    @Past(message = "Date must be in past time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}