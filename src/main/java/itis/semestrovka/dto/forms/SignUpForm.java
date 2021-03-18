package itis.semestrovka.dto.forms;

import itis.semestrovka.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpForm {
    @Email(message = "Incorrect email")
    private String email;
    @NotBlank(message = "Password must be not empty")
    private String password;
    @Pattern(regexp = "^(?!.*\\.\\.)(?!\\.)(?!.*\\.$)(?!\\d+$)[a-zA-Z0-9.]{5,50}$",
    message = "Incorrect username")
    private String username;
    private Date birth;

    public static User toUser(SignUpForm form) {
        return User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .username(form.getUsername())
                .birth(form.getBirth())
                .build();
    }
}
