package itis.semestrovka.dto;

import itis.semestrovka.models.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistDto {
    private Long id;

    private String name;

    private User user;
}
