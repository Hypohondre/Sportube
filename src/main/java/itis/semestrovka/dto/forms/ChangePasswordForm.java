package itis.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordForm {
    private String oldPassword;
    private String repeatNewPassword;
    private String newPassword;
}
