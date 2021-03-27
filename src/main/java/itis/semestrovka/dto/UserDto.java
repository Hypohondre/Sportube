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

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .birth(user.getBirth())
                .role(user.getRole().toString())
                .state(user.getState().toString())
                .build();
    }
}
