package itis.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInForm {
    private String login;
    private String password;
    private String rememberMe;
}
