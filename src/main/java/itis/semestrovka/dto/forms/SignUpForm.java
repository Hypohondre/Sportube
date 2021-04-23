package itis.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpForm {
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password must be not empty")
    @Size(min = 8, max = 50, message = "Password must contain more than 8 and less than 50 characters")
    private String password;
    @Pattern(regexp = "^(?!.*\\.\\.)(?!\\.)(?!.*\\.$)(?!\\d+$)[a-zA-Z0-9.]{5,50}$",
    message = "Incorrect username")
    private String username;
    @Past(message = "Date must be in past time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}