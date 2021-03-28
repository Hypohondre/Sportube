package itis.semestrovka.dto;

import itis.semestrovka.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private Date birth;
    private String role;
    private String state;
    private String photo;
    private String code;
}
