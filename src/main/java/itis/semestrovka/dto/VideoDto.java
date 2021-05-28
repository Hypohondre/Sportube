package itis.semestrovka.dto;

import itis.semestrovka.models.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDto {
    private Long id;

    private String preview;

    private String name;

    private String file;

    private Long size;

    private String description;

    private User creator;
}
